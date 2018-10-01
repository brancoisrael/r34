package br.com.r34.negocio.service.membro;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.r34.negocio.domain.dto.membro.MembroDTO;
import br.com.r34.negocio.domain.vo.membro.Membro;

@Service
@Transactional
public interface ServiceMembro {

	MembroDTO inserir(Membro membro);
	
	MembroDTO deletar(long id);
	
	MembroDTO atualizar(Membro membro);
	
	MembroDTO login(MembroDTO membroDTO);
}
