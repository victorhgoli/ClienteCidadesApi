package br.com.uol.api.model.input;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.uol.domain.model.SexoCliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	private SexoCliente sexo;

	private LocalDate dataNascimento;

	@Valid
	@NotNull
	private CidadeIdInput cidade;

}
