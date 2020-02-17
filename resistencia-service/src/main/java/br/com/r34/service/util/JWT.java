package br.com.r34.service.util;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import br.com.r34.persistencia.enums.PerfilAcesso;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWT {

	public static String criarToken(String login,PerfilAcesso perfilAcesso) {
		String jwtToken = Jwts.builder().
				signWith(SignatureAlgorithm.HS256, generateKey())
				.setHeaderParam("typ", "JWT")
				.setSubject(login)
				.claim("perfilAcesso", perfilAcesso)
				.setIssuer("Resistencia34MC")
				.setIssuedAt(new Date())
				.setExpiration(toDate(LocalDateTime.now().plusMinutes(60L))).compact();
		return jwtToken;
	}

	public static boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String revalidarToken(String token) {
		if(validarToken(token)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);
			if(toDate(LocalDateTime.now().plusMinutes(10L)).after(claimsJws.getBody().getExpiration())) {
				return criarToken(claimsJws.getBody().getSubject(), PerfilAcesso.valueOf((String)claimsJws.getBody().get("perfilAcesso")));	
			}			
		}		
		return token;
	}
	
	public static PerfilAcesso recuperarPerfil(String token) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);
		return PerfilAcesso.valueOf((String) claimsJws.getBody().get("perfilAcesso"));
	}
	
	public static String recuperarLogin(String token) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);
		return claimsJws.getBody().getSubject();
	}

	private static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	private static Key generateKey() {
		String keyString = "cl9Opu/cc0FQiEiPXLI0Z4T8NFI=";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "HmacSHA256");
		return key;
	}
}
