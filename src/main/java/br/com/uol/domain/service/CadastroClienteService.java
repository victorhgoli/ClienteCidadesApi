package br.com.uol.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uol.domain.exception.EntidadeNaoEncontradaException;
import br.com.uol.domain.model.Cidade;
import br.com.uol.domain.model.Cliente;
import br.com.uol.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	public Cliente buscarOuFalhar(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de cliente com código %d", clienteId)));
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		Cidade c = cadastroCidade.buscarOuFalhar(cliente.getCidade().getId());

		cliente.setCidade(c);

		return clienteRepository.save(cliente);
	}

	@Transactional
	public void excluir(Long cidadeId) {
		clienteRepository.deleteById(cidadeId);
	}

}
