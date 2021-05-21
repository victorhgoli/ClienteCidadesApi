package br.com.uol.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uol.domain.exception.EntidadeNaoEncontradaException;
import br.com.uol.domain.model.Cidade;
import br.com.uol.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de cidade com código %d", cidadeId)));
	}


	@Transactional
	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

}
