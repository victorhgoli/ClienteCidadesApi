package br.com.uol.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uol.domain.exception.ClienteNaoEncontradoException;
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
	private CadastroCidadeService cadastroCidade;

	public Cliente buscarOuFalhar(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
	}
	
	public List<Cliente> pesquisar(ClienteFilter filter) {
		return clienteRepository.findAll(ClienteSpecs.filter(filter));
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		Cidade c = cadastroCidade.buscarOuFalhar(cliente.getCidade().getId());

		cliente.setCidade(c);

		return clienteRepository.save(cliente);
	}

	@Transactional
	public Cliente atualizarNome(Long clienteId, String nome) {
		Cliente cliente = buscarOuFalhar(clienteId);
		cliente.setNome(nome);

		return salvar(cliente);
	}

	@Transactional
	public void excluir(Long cidadeId) {
		clienteRepository.deleteById(cidadeId);
	}

	
}
