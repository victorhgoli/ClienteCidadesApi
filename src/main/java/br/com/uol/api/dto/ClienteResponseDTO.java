package br.com.uol.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.uol.domain.model.SexoCliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "ClienteResponse", description = "Respresenta um novo cliente")
@Getter
@Setter
public class ClienteResponseDTO {
	
	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(value = "Nome do cliente", example = "Dino")
	@NotEmpty
	private String nome;

	@ApiModelProperty(value = "Sexo do cliente", example = "MASCULINO")
	@NotNull
	private SexoCliente sexo;
	
	@ApiModelProperty(value = "Data de nascimento do cliente", example = "2019-10-08", dataType = "Date")
	private LocalDate dataNascimento;
	
	@ApiModelProperty(value = "Idade do Cliente", example = "40")
	private int idade;

	private CidadeResponseDTO cidade;

}
