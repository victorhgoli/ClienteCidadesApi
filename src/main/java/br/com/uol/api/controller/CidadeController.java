package br.com.uol.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.uol.domain.filter.CidadeFilter;
import br.com.uol.domain.model.Cidade;
import br.com.uol.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cidadeService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cidade cadastrar(@Valid @RequestBody Cidade cidade) {
		cidade = cidadeService.salvar(cidade);

		return cidade;
	}

	@GetMapping
	public List<Cidade> pesquisar(CidadeFilter filter) {
		List<Cidade> cidades = cidadeService.filtrarCidade(filter);
				
		return cidades;
	}
	
}
