package br.com.uol.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uol.api.dto.CidadeResponseDTO;
import br.com.uol.domain.model.Cidade;

@Component
public class CidadeResponseDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CidadeResponseDTO toDto(Cidade estado) {
		return modelMapper.map(estado, CidadeResponseDTO.class);
	}

	public List<CidadeResponseDTO> toCollectionDto(List<Cidade> cidades) {
		return cidades.stream().map(cidade -> toDto(cidade)).collect(Collectors.toList());
	}

}
