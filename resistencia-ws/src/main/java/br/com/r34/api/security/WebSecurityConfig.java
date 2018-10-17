package br.com.r34.api.security;

import java.util.List;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.r34.api.conf.ResistenciaPostgreSQLConfig;
import br.com.r34.negocio.domain.vo.acesso.Endpoint;
import br.com.r34.negocio.domain.vo.acesso.RegraAcesso;
import br.com.r34.negocio.service.acesso.ServiceRegraAcesso;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//@Autowired
	//private ResistenciaPostgreSQLConfig resistenciaPostgreSQLConfig;
	
	@Autowired
	private ServiceRegraAcesso serviceRegraAcesso;
		
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
			
		httpSecurity.csrf().disable().authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.anyRequest().authenticated()
		.and()
		
		// filtra requisições de login
		.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
		
		// filtra outras requisições para verificar a presença do JWT no header
		.addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		.withUser("admin")
		.password("$2a$10$1iLQge/irBBIDHb8scvPxOKVmFlgBWX1Br7owYR52w.IZvNJh2oWe")		
		.roles("ADMIN");*/
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(new BCryptPasswordEncoder())
        .usersByUsernameQuery("select email , senha, status from tb_membro where email=?")
        .authoritiesByUsernameQuery("select email, nome from tb_membro where email=?");
        
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


/*
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
 
  private static final String USUARIO_POR_LOGIN = "SELECT login, senha, ativo, nome FROM Usuario"
            + " WHERE login = ?";
   
  private static final String PERMISSOES_POR_USUARIO = "SELECT u.login, p.nome FROM usuario_permissao up"
            + " JOIN usuario u ON u.id = up.usuarios_id"
            + " JOIN permissao p ON p.id = up.permissoes_id"
            + " WHERE u.login = ?";
   
  private static final String PERMISSOES_POR_GRUPO = "SELECT g.id, g.nome, p.nome FROM grupo_permissao gp"
            + " JOIN grupo g ON g.id = gp.grupos_id"
            + " JOIN permissao p ON p.id = gp.permissoes_id"
            + " JOIN usuario_grupo ug ON ug.grupos_id = g.id"
            + " JOIN usuario u ON u.id = ug.usuarios_id"
            + " WHERE u.login = ?";
     
  @Autowired
  private DataSource dataSource;
 
  ...
   
  @Override
  protected void configure(AuthenticationManagerBuilder builder) throws Exception {
    builder
        .jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(new BCryptPasswordEncoder())
        .usersByUsernameQuery(USUARIO_POR_LOGIN)
        .authoritiesByUsernameQuery(PERMISSOES_POR_USUARIO)
        .groupAuthoritiesByUsername(PERMISSOES_POR_GRUPO)
        .rolePrefix("ROLE_");
  }
}
*/