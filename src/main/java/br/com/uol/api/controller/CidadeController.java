package br.com.uol.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.uol.api.assembler.CidadeInputDisassembler;
import br.com.uol.api.assembler.CidadeModelAssembler;
import br.com.uol.api.model.CidadeModel;
import br.com.uol.api.model.input.CidadeInput;
import br.com.uol.domain.model.Cidade;
import br.com.uol.domain.repository.CidadeRepository;
import br.com.uol.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CidadeModel cadastrar(@Valid @RequestBody CidadeInput cidadeInput) {
		Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

		cidade = cadastroCidade.salvar(cidade);

		return cidadeModelAssembler.toModel(cidade);
	}

	@GetMapping("/por-nome")
	public List<CidadeModel> buscarPorNome(@RequestParam String nome) {
		List<Cidade> cidades = cidadeRepository.findByNome(nome);

		return cidadeModelAssembler.toCollectionModel(cidades);
	}

	@GetMapping("/por-estado")
	public List<CidadeModel> buscarPorEstado(@RequestParam String estado) {
		List<Cidade> cidades = cidadeRepository.findByEstado(estado);

		return cidadeModelAssembler.toCollectionModel(cidades);
	}
}
