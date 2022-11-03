package br.com.mjv.projeto.services;

import java.util.Optional;

import br.com.mjv.projeto.entities.Pedido;
import br.com.mjv.projeto.entities.dtos.PedidoDTO;

public interface PedidoService {

	Pedido salvar(PedidoDTO dto);

	Optional<Pedido> obterPedido(Integer id);
}
