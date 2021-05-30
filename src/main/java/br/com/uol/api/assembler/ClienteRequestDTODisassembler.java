package br.com.uol.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uol.api.dto.ClienteRequestDTO;
import br.com.uol.domain.model.Cliente;

@Component
public class ClienteRequestDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cliente toDomainObject(ClienteRequestDTO clienteDto) {
		return modelMapper.map(clienteDto, Cliente.class);
	}
	
	public void copyToDomainObject(ClienteRequestDTO clienteRequestDTO, Cliente cliente) {
		modelMapper.map(clienteRequestDTO, cliente);
	}

}