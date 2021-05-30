package br.com.uol.api.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.uol.domain.model.SexoCliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "ClienteRequest", description = "Respresenta um cliente")
@Getter
@Setter
public class ClienteRequestDTO {

	@ApiModelProperty(value = "Nome do cliente", example = "Dino", required = true)
	@NotEmpty
	private String nome;

	@ApiModelProperty(value = "Sexo do cliente", example = "MASCULINO", required = true)
	@NotNull
	private SexoCliente sexo;

	@ApiModelProperty(value = "Data de nascimento do cliente", example = "2019-10-08", dataType = "Date", required = true)
	private LocalDate dataNascimento;

	@Valid
	@NotNull
	private CidadeIdRequestDTO cidade;

	
	@ApiModel(value = "CidadeIdRequest", description = "Respresenta o ID da cidade")
	@Getter
	@Setter
	static class CidadeIdRequestDTO {

		@ApiModelProperty(value = "Id", example = "1", required = true)
		@NotNull
		private Long id;
	}

}