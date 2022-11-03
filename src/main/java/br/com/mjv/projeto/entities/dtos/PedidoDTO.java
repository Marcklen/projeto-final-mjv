package br.com.mjv.projeto.entities.dtos;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.mjv.projeto.configs.annotations.ListaNaoVazia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

	@NotNull(message = "informe o CODIGO do CLIENTE")
	private Integer cliente;
	
	@NotNull(message = "campo TOTAL PEDIDO é obrigatorio")
	private BigDecimal total;
	
	//abaixo uma anotação customizada para validar o campo itens (annotation valid custom)
	@ListaNaoVazia(message = "Não foi possível realizar um pedido, a lista de itens está vazia!")
	private List<ItemPedidoDTO> itens;
}
