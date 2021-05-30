package br.com.uol.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@Builder
public class Problem {

	@ApiModelProperty(example = "400")
    private Integer status;
	
	@ApiModelProperty(example = "2019-11-10T18:00:00Z")
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(example = "https://testeCompasso.com.br/dados-invalidos")
    private String type;
	
	@ApiModelProperty(example = "Dados inválidos")
    private String title;

	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String detail;

	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String userMessage;
	
	@ApiModelProperty("Objetos ou campos que geraram o erro (Opcional)")
    private List<Object> objects;

    @ApiModel("Objeto do Problema")
    @Getter
    @Setter
    @Builder
    public static class Object {
    	
    	@ApiModelProperty(example = "nome")
        private String name;
    	
    	@ApiModelProperty(example = "O nome do cliente é obrigatorio")
        private String userMessage;
    }

}
