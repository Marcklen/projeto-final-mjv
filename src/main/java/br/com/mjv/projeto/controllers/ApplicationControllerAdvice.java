package br.com.mjv.projeto.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mjv.projeto.exceptions.ApiErros;
import br.com.mjv.projeto.exceptions.PedidoNaoEncontradoException;
import br.com.mjv.projeto.exceptions.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros manipuladorRegraException (RegraNegocioException ex) {
		String mensagem = ex.getMessage();
		return new ApiErros(mensagem);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErros manipuladorPedidoNaoEncontradoException(PedidoNaoEncontradoException ex) {
		return new ApiErros(ex.getMessage());
	}
}
