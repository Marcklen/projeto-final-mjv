package br.com.mjv.projeto.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mjv.projeto.entities.Cliente;
import br.com.mjv.projeto.entities.ItemPedido;
import br.com.mjv.projeto.entities.Pedido;
import br.com.mjv.projeto.entities.Produto;
import br.com.mjv.projeto.entities.dtos.ItemPedidoDTO;
import br.com.mjv.projeto.entities.dtos.PedidoDTO;
import br.com.mjv.projeto.exceptions.RegraNegocioException;
import br.com.mjv.projeto.repositories.ClienteRepository;
import br.com.mjv.projeto.repositories.ItemPedidoRepository;
import br.com.mjv.projeto.repositories.PedidoRepository;
import br.com.mjv.projeto.repositories.ProdutoRepository;
import br.com.mjv.projeto.services.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // usado para instanciar no momento da criação da classe
public class PedidoServiceImpl implements PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoRepository produtoRepository;
	private final ItemPedidoRepository itemPedidoRepository;
	
	@Override
	@Transactional // anotação para salvar tudo ou nao salvar e dar um rollback
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente	cliente = clienteRepository
				.findById(idCliente)
				.orElseThrow( () -> new RegraNegocioException("Código do Cliente Inválido."));		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);

		List<ItemPedido> itensPedido = converterItensPedido(pedido, dto.getItens());
		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(itensPedido);
		pedido.setItens(itensPedido);
		return pedido;
	}
	
	//metodo responsável por converter a lista de itens em itensPedido
	private List<ItemPedido> converterItensPedido (Pedido pedido, List<ItemPedidoDTO> itens) {
		if(itens.isEmpty()) {
			throw new RegraNegocioException("Não foi possível realizar um pedido, a lista de itens está vazia!");
		}
		
		return itens
				.stream()
				.map( dto -> {
			Integer idProduto = dto.getProduto();
			Produto produto = produtoRepository.findById(idProduto).orElseThrow(
					() -> new RegraNegocioException("Código do Produto Inválido: " + idProduto));
			
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
		}).collect(Collectors.toList());
	}
}