package br.com.uol.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uol.api.model.ClienteModel;
import br.com.uol.domain.model.Cliente;

@Component
public class ClienteModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ClienteModel toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteModel.class);
	}

	public List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
		return clientes.stream().map(cliente -> toModel(cliente)).collect(Collectors.toList());
	}

}
