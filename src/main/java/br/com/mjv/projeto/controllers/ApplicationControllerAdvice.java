package br.com.mjv.projeto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mjv.projeto.exceptions.ApiErros;
import br.com.mjv.projeto.exceptions.ClienteNaoEncontradoException;
import br.com.mjv.projeto.exceptions.PedidoNaoEncontradoException;
import br.com.mjv.projeto.exceptions.ProdutoNaoEncontradoException;
import br.com.mjv.projeto.exceptions.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros manipuladorRegraException (RegraNegocioException ex) {
		String mensagem = ex.getMessage();
		return new ApiErros(mensagem);
	}
	
	@ExceptionHandler(ClienteNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErros manipuladorPedidoNaoEncontradoException(ClienteNaoEncontradoException ex) {
		return new ApiErros(ex.getMessage());
	}
	
	@ExceptionHandler(ProdutoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErros manipuladorPedidoNaoEncontradoException(ProdutoNaoEncontradoException ex) {
		return new ApiErros(ex.getMessage());
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErros manipuladorPedidoNaoEncontradoException(PedidoNaoEncontradoException ex) {
		return new ApiErros(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros manipuladorArgumentoInvalidoException(MethodArgumentNotValidException ex) {
		List<String> erro = ex.getBindingResult()
			.getAllErrors()
			.stream()
			.map( e -> e.getDefaultMessage()).collect(Collectors.toList());
		return new ApiErros(erro);
	}
}