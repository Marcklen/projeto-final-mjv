package br.com.mjv.projeto.services;

import br.com.mjv.projeto.entities.Pedido;
import br.com.mjv.projeto.entities.dtos.PedidoDTO;

public interface PedidoService {

	Pedido salvar(PedidoDTO dto);
}
