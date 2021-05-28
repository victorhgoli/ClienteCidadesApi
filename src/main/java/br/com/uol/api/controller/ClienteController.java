package br.com.uol.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.uol.api.dto.ClienteDTO;
import br.com.uol.domain.filter.ClienteFilter;
import br.com.uol.domain.model.Cliente;
import br.com.uol.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private CadastroClienteService cadastroCliente;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cliente cadastrar(@Valid @RequestBody Cliente cliente) {
		cliente = cadastroCliente.salvar(cliente);

		return cliente;
	}

	@GetMapping
	public List<Cliente> pesquisar(ClienteFilter filter) {
		List<Cliente> clientes = cadastroCliente.pesquisar(filter);

		return clientes;
	}

	@GetMapping("/{clienteId}")
	public Cliente buscar(@PathVariable Long clienteId) {
		Cliente cliente = cadastroCliente.buscarOuFalhar(clienteId);

		return cliente;
	}

	@DeleteMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long clienteId) {
		cadastroCliente.excluir(clienteId);
	}

	@PatchMapping("/{clienteId}")
	public Cliente atualizar(@PathVariable Long clienteId, @Valid @RequestBody ClienteDTO clienteDto) {
		Cliente cliente = cadastroCliente.buscarOuFalhar(clienteId);

		cliente.setNome(clienteDto.getNome());

		return cadastroCliente.salvar(cliente);
	}

}
