package br.com.uol.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uol.domain.exception.CidadeNaoEncontradaException;
import br.com.uol.domain.filter.CidadeFilter;
import br.com.uol.domain.model.Cidade;
import br.com.uol.domain.repository.CidadeRepository;
import br.com.uol.infraestructure.specs.CidadeSpecs;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}
	
	@Transactional
	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	public List<Cidade> filtrarCidade(CidadeFilter filter) {
		return cidadeRepository.findAll(CidadeSpecs.filter(filter));
	}

}
