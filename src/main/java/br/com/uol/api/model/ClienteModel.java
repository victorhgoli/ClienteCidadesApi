package br.com.uol.api.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModel {
	
	private Long id;
	private String nome;
	private String sexo;
	private LocalDate dataNascimento;
	private int idade;
	private CidadeModel cidade;

}
