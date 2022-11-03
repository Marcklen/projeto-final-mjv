package br.com.mjv.projeto.services;

import java.util.Optional;

import br.com.mjv.projeto.entities.Pedido;
import br.com.mjv.projeto.entities.dtos.PedidoDTO;
import br.com.mjv.projeto.entities.enums.StatusPedido;

public interface PedidoService {

	Pedido salvar(PedidoDTO dto);

	Optional<Pedido> obterPedido(Integer id);

	void atualizarStatus(Integer id, StatusPedido status);
}
