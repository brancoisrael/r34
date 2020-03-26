package br.com.r34.api.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.r34.service.acesso.ServiceEndPointImpl;
import br.com.r34.service.util.JWT;

public class AutorizationInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private ServiceEndPointImpl serviceEndPointImpl; 
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (request.getMethod().equals("OPTIONS")) {
			response.addHeader("Access-Control-Allow-Headers", "Content-Type, x-xsrf-token");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, OPTIONS, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,Authorization,If-Modified-Since,Cache-Control,Content-Type");
            response.addHeader("Access-Control-Max-Age", "3600");
            response.addHeader("charset", "utf-8");           
        }			
	   	   
       if(request.getRequestURL().toString().contains("/acesso/login") || request.getRequestURL().toString().contains("/membros/salvar"))
        return super.preHandle(request, response, handler);
       
       String token = request.getHeader("authorization");
       String url = request.getRequestURI();
       
       if(JWT.validarToken(token)) {
    	   //Validar a autorização
    	   return super.preHandle(request, response, handler);
       }
       
       
       return false;
    }
	
}
