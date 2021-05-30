package br.com.uol.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.uol.domain.exception.CidadeNaoEncontradaException;
import br.com.uol.domain.exception.ClienteNaoEncontradoException;
import br.com.uol.domain.exception.NegocioException;
import br.com.uol.domain.filter.ClienteFilter;
import br.com.uol.domain.model.Cidade;
import br.com.uol.domain.model.Cliente;
import br.com.uol.domain.repository.ClienteRepository;
import br.com.uol.infraestructure.specs.ClienteSpecs;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CadastroCidadeService cidadeService;

	public Cliente buscarOuFalhar(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
	}

	public List<Cliente> pesquisar(ClienteFilter filter) {
		return clienteRepository.findAll(ClienteSpecs.filter(filter));
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		try {
			Cidade cidade = cidadeService.buscarOuFalhar(cliente.getCidade().getId());
			cliente.setCidade(cidade);

			return clienteRepository.save(cliente);
		} catch (CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@Transactional
	public void excluir(Long cidadeId) {
		clienteRepository.deleteById(cidadeId);
	}

	@Transactional
	public Cliente atualizarParcial(Map<String, Object> dadosOrigem, Cliente clienteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Cliente clienteOrigem = objectMapper.convertValue(dadosOrigem, Cliente.class);

		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Cliente.class, nomePropriedade);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, clienteOrigem);

			ReflectionUtils.setField(field, clienteDestino, novoValor);
		});

		return salvar(clienteDestino);
	}

}
