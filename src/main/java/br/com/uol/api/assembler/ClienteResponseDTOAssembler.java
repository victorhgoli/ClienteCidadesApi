package br.com.uol.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uol.api.dto.ClienteResponseDTO;
import br.com.uol.domain.model.Cliente;

@Component
public class ClienteResponseDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ClienteResponseDTO toDto(Cliente cliente) {
		return modelMapper.map(cliente, ClienteResponseDTO.class);
	}

	public List<ClienteResponseDTO> toCollectionDto(List<Cliente> clientes) {
		return clientes.stream().map(cliente -> toDto(cliente)).collect(Collectors.toList());
	}

}
