package br.com.uol.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uol.api.model.input.ClienteInput;
import br.com.uol.domain.model.Cidade;
import br.com.uol.domain.model.Cliente;

@Component
public class ClienteInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cliente toDomainObject(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}

	public void copyToDomainObject(ClienteInput clienteInput, Cliente cliente) {
        cliente.setCidade(new Cidade());

		modelMapper.map(clienteInput, cliente);
	}

}