package br.com.r34.api.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.r34.service.util.JWT;

public class AutorizationInterceptor extends HandlerInterceptorAdapter{

	
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, OPTIONS, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,Authorization,If-Modified-Since,Cache-Control,Content-Type");
            response.addHeader("Access-Control-Max-Age", "3600");
            response.addHeader("charset", "utf-8");           
        }	
		
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
