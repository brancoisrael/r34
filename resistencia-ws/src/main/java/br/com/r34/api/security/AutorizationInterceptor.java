package br.com.r34.api.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.r34.service.util.JWT;

public class AutorizationInterceptor extends HandlerInterceptorAdapter{

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	   String token = request.getHeader("authorization");
       /*System.out.println(request.getHeader("authorization"));
       System.out.println(request.getRequestURL().toString());*/
       if(request.getRequestURL().toString().contains("/acesso/login"))   
        return super.preHandle(request, response, handler);
       
      
     
       if(JWT.validarToken(token)) {
    	   //Validar a autorização
    	   return super.preHandle(request, response, handler);
       }
       
       
       return false;
    }
	
}
