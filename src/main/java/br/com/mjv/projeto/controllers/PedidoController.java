package br.com.mjv.projeto.controllers;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mjv.projeto.entities.ItemPedido;
import br.com.mjv.projeto.entities.Pedido;
import br.com.mjv.projeto.entities.dtos.AtualizarStatusPedidoDTO;
import br.com.mjv.projeto.entities.dtos.InformacaoItemPedidoDTO;
import br.com.mjv.projeto.entities.dtos.InformacoesPedidoDTO;
import br.com.mjv.projeto.entities.dtos.PedidoDTO;
import br.com.mjv.projeto.entities.enums.StatusPedido;
import br.com.mjv.projeto.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Integer salvarPedido(@RequestBody PedidoDTO dto) {
		Pedido pedido = pedidoService.salvar(dto);
		return pedido.getId();
	}

	@GetMapping("{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return pedidoService
				.obterPedido(id)
				.map(p -> converter(p))
				.orElseThrow(() 
						-> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado!"));
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void atualizarStatus (@PathVariable Integer id, @RequestBody AtualizarStatusPedidoDTO dto) {
		String novoStatus = dto.getNovoStatus();
		pedidoService.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
	}

	private InformacoesPedidoDTO converter(Pedido pedido) {
		return InformacoesPedidoDTO
				.builder()
				.codigo(pedido.getId())
				.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.cpf(pedido.getCliente().getCpf()).total(pedido.getTotal())
				.nomeCliente(pedido.getCliente().getNome())
				.total(pedido.getTotal())
				.status(pedido.getStatus().name()) //.name é para transformar o valor do ENUM em STRING
				.itens(converterItens(pedido.getItens()))
				.build();
	}

	private List<InformacaoItemPedidoDTO> converterItens(List<ItemPedido> itens) {
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map( item -> InformacaoItemPedidoDTO
				.builder()
				.descricaoProduto(item.getProduto().getDescricao())
				.precoUnitario(item.getProduto().getPreco())
				.quantidade(item.getQuantidade())
				.build()).collect(Collectors.toList());
	}
}
