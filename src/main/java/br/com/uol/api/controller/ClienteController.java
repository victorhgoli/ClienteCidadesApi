package br.com.uol.api.controller;

import java.util.List;
import java.util.Map;

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

import br.com.uol.api.assembler.ClienteRequestDTODisassembler;
import br.com.uol.api.assembler.ClienteResponseDTOAssembler;
import br.com.uol.api.dto.ClienteRequestDTO;
import br.com.uol.api.dto.ClienteResponseDTO;
import br.com.uol.domain.filter.ClienteFilter;
import br.com.uol.domain.model.Cliente;
import br.com.uol.domain.service.CadastroClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private CadastroClienteService clienteService;

	@Autowired
	private ClienteRequestDTODisassembler clienteDisassembler;

	@Autowired
	private ClienteResponseDTOAssembler clienteAssembler;

	@ApiOperation("Cadastra um cliente")
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ClienteResponseDTO cadastrar(@Valid @RequestBody ClienteRequestDTO clienteInput) {
		Cliente cliente = clienteDisassembler.toDomainObject(clienteInput);

		cliente = clienteService.salvar(cliente);

		return clienteAssembler.toDto(cliente);
	}

	@ApiOperation("Pesquisa clientes")
	@GetMapping
	public List<ClienteResponseDTO> pesquisar(ClienteFilter filter) {
		List<Cliente> clientes = clienteService.pesquisar(filter);

		return clienteAssembler.toCollectionDto(clientes);
	}

	@ApiOperation("Busca um cliente por ID")
	@GetMapping("/{clienteId}")
	public ClienteResponseDTO buscar(@PathVariable Long clienteId) {
		Cliente cliente = clienteService.buscarOuFalhar(clienteId);

		return clienteAssembler.toDto(cliente);
	}

	@ApiOperation("Exclui um cliente por ID")
	@DeleteMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@ApiParam(name = "clienteId", value = "Id do cliente")  @PathVariable Long clienteId) {
		clienteService.excluir(clienteId);
	}

	@ApiOperation("Atualiza um cliente por ID")
	@PatchMapping("/{clienteId}")
	public ClienteResponseDTO atualizarParcial(@ApiParam(name = "clienteId", value = "Id do cliente") @PathVariable Long clienteId,
												@RequestBody Map<String, Object> campos) {
		Cliente cliente = clienteService.buscarOuFalhar(clienteId);
		cliente = clienteService.atualizarParcial(campos, cliente);

		return clienteAssembler.toDto(cliente);
	}
}
