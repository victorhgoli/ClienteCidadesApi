package br.com.uol.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteNomeInput {
	
	@NotBlank
	private String nome;

}
