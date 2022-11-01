package br.com.mjv.projeto.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.mjv.projeto.services.PedidoService;

@RestController
public class PedidoController {

	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		super();
		this.pedidoService = pedidoService;
	}
	
}
