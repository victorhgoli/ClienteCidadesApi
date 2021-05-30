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

import br.com.uol.api.assembler.CidadeRequestDTODisassembler;
import br.com.uol.api.assembler.CidadeResponseDTOAssembler;
import br.com.uol.api.dto.CidadeRequestDTO;
import br.com.uol.api.dto.CidadeResponseDTO;
import br.com.uol.domain.filter.CidadeFilter;
import br.com.uol.domain.model.Cidade;
import br.com.uol.domain.service.CadastroCidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cidadeService;
	
	@Autowired
	private CidadeResponseDTOAssembler cidadeAssembler;
	
	@Autowired
	private CidadeRequestDTODisassembler cidadeDisassembler;

	@ApiOperation("Cadastra uma cidade")
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CidadeResponseDTO cadastrar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
										@Valid @RequestBody CidadeRequestDTO cidadeDto) {
		
		Cidade cidade = cidadeDisassembler.toDomainObject(cidadeDto);
		cidade = cidadeService.salvar(cidade);

		return cidadeAssembler.toDto(cidade);
	}

	@ApiOperation("Pesquisa cidades")
	@GetMapping
	public List<CidadeResponseDTO> pesquisar(CidadeFilter filter) {
		List<Cidade> cidadesFilter = cidadeService.pesquisar(filter);

		return cidadeAssembler.toCollectionDto(cidadesFilter);
	}
	
}
