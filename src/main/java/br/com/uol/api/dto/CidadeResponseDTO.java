package br.com.uol.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "CidadeResponse", description = "Respresenta uma cidade")
@Getter
@Setter
public class CidadeResponseDTO {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(value = "Nome da cidade", example = "Uberlandia")
	private String nome;

	@ApiModelProperty(value = "Estado da cidade", example = "MG")
	private String estado;

}
