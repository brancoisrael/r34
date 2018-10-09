package br.com.r34.api.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.r34.api.conf.ResistenciaPostgreSQLConfig;
import br.com.r34.negocio.domain.vo.acesso.Endpoint;
import br.com.r34.negocio.domain.vo.acesso.RegraAcesso;
import br.com.r34.negocio.service.acesso.ServiceRegraAcesso;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ResistenciaPostgreSQLConfig resistenciaPostgreSQLConfig;
	
	@Autowired
	private ServiceRegraAcesso serviceRegraAcesso;
		
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
			
			ajustePerfilAcesso(httpSecurity)
			
			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
			
			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// cria uma conta default
		/*auth. inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("admin")
			.password("admin")
			.roles("ADMIN");
			*/
		auth.jdbcAuthentication().dataSource(resistenciaPostgreSQLConfig.dataSource())
        .usersByUsernameQuery("select email , senha from Membro where email=?")
        .authoritiesByUsernameQuery("select email from Membro where email=?")
        .passwordEncoder(new BCryptPasswordEncoder());
	}
	
	private HttpSecurity ajustePerfilAcesso(HttpSecurity httpSecurity) throws Exception{
		List<RegraAcesso> regrasAcesso = serviceRegraAcesso.pesquisarTodos();
		
		httpSecurity.csrf().disable().authorizeRequests()
		.antMatchers("/swagger-ui.html").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/js/**").permitAll()
		.anyRequest().authenticated();
		
		for(RegraAcesso regraAcesso : regrasAcesso) {
			for(Endpoint endpoint : regraAcesso.getEndpoints()) {
				httpSecurity.authorizeRequests().antMatchers(endpoint.getUrl()).hasAuthority(regraAcesso.getPerfilAcesso().getDescricao());
			}
		}
		
		return httpSecurity;
	}
}
