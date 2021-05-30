package br.com.uol.api.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "CidadeRequest", description = "Respresenta uma nova cidade")
@Getter
@Setter
public class CidadeRequestDTO {
	
	@ApiModelProperty(value = "Nome da cidade", example = "Uberlandia", required = true)
	@NotEmpty
	private String nome;
	
	@ApiModelProperty(value = "Nome do estado", example = "MG", required = true)
	@NotEmpty
	private String estado;

}
