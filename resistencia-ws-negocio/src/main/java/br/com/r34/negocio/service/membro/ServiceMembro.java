package br.com.r34.negocio.service.membro;

import java.util.List;

import br.com.r34.negocio.domain.dto.membro.MembroDTO;
import br.com.r34.negocio.domain.vo.membro.Membro;

public interface ServiceMembro {

	MembroDTO inserir(Membro membro);
	
	MembroDTO deletar(long id);
	
	MembroDTO atualizar(Membro membro);
	
	MembroDTO login(MembroDTO membroDTO);
	
	Iterable<Membro> selectAll();
}
