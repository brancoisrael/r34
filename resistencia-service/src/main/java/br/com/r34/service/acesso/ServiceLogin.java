package br.com.r34.service.acesso;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.r34.persistencia.dto.acesso.TokenDTO;
import br.com.r34.persistencia.enums.Patente;
import br.com.r34.persistencia.enums.PerfilAcesso;
import br.com.r34.persistencia.enums.SituacaoMembro;
import br.com.r34.persistencia.repository.membro.MembroDAO;
import br.com.r34.persistencia.vo.membro.Membro;
import br.com.r34.service.util.CipherUtil;
import br.com.r34.service.util.JWT;

@Service
public class ServiceLogin {

	@Autowired
	private MembroDAO membroDAO;
	
	public TokenDTO login(String email, String senha) {
		TokenDTO token = new TokenDTO();
		
		try {
			senha = CipherUtil.cipher(senha);
			Membro membro = membroDAO.login(email, senha, SituacaoMembro.ATIVO);
			
			if(membro!=null) {
				token.setToken(JWT.criarToken(membro.getEmail(), definirPerfil(membro)));			
			}
		}
		catch(NoSuchAlgorithmException e) {}
		
		return token;
	}
	
	private PerfilAcesso definirPerfil(Membro membro) {
		if (membro.getPatente().ordinal() < Patente.ESCUDADO.ordinal()) {
			return PerfilAcesso.NAO_ESCUDADO;
		}

		switch (membro.getCargo()) {
			case TESOUREIRO:
				return PerfilAcesso.TESOURARIA;

		default:
			return PerfilAcesso.ESCUDADO;
		}
	}
}
