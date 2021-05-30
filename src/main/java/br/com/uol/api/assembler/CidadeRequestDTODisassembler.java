package br.com.uol.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uol.api.dto.CidadeRequestDTO;
import br.com.uol.domain.model.Cidade;

@Component
public class CidadeRequestDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cidade toDomainObject(CidadeRequestDTO cidadeDto) {
		return modelMapper.map(cidadeDto, Cidade.class);
	}

}