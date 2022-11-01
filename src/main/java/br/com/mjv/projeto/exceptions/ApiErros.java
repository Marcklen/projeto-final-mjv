package br.com.mjv.projeto.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;


public class ApiErros {
	
	@Getter
	private List<String> erros;
	
	public ApiErros(String mensagemErro) {
		this.erros = Arrays.asList(mensagemErro);
	}
}
