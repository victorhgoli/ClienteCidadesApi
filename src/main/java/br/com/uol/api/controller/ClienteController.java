package br.com.uol.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.uol.api.assembler.ClienteInputDisassembler;
import br.com.uol.api.assembler.ClienteModelAssembler;
import br.com.uol.api.model.ClienteModel;
import br.com.uol.api.model.input.ClienteInput;
import br.com.uol.api.model.input.ClienteNomeInput;
import br.com.uol.domain.model.Cliente;
import br.com.uol.domain.repository.ClienteRepository;
import br.com.uol.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private CadastroClienteService cadastroCliente;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteModelAssembler clienteModelAssembler;
	
	@Autowired
	private ClienteInputDisassembler clienteInputDisassembler;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ClienteModel cadastrar(@Valid @RequestBody ClienteInput clienteInput) {
		Cliente cliente = clienteInputDisassembler.toDomainObject(clienteInput);
		
		cliente = cadastroCliente.salvar(cliente);
		
		return clienteModelAssembler.toModel(cliente);
	}

	@GetMapping("/por-nome")
	public List<ClienteModel> buscarPorNome(@RequestParam String nome) {
		List<Cliente> clientes = clienteRepository.findByNome(nome);

		return clienteModelAssembler.toCollectionModel(clientes);
	}

	@GetMapping("/{clienteId}")
	public ClienteModel buscarPorId(@PathVariable Long clienteId) {
		Cliente cliente = cadastroCliente.buscarOuFalhar(clienteId);

		return clienteModelAssembler.toModel(cliente);
	}

	@DeleteMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long clienteId) {
		cadastroCliente.excluir(clienteId);
	}

	@PutMapping("/{clienteId}")
	public ClienteModel atualizarNome(@PathVariable Long clienteId, @Valid @RequestBody ClienteNomeInput clienteNomeInput) {
		Cliente cliente = cadastroCliente.buscarOuFalhar(clienteId);
		cliente.setNome(clienteNomeInput.getNome());

		cliente = cadastroCliente.salvar(cliente);
		return clienteModelAssembler.toModel(cliente);
	}

}
