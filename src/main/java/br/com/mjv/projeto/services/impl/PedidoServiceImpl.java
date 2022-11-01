package br.com.mjv.projeto.services.impl;

import org.springframework.stereotype.Service;

import br.com.mjv.projeto.repositories.PedidoRepository;
import br.com.mjv.projeto.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	private PedidoRepository pedidoRepository;

	public PedidoServiceImpl(PedidoRepository pedidoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
	}
}
