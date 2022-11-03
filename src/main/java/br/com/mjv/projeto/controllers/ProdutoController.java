package br.com.mjv.projeto.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mjv.projeto.entities.Produto;
import br.com.mjv.projeto.exceptions.ProdutoNaoEncontradoException;
import br.com.mjv.projeto.repositories.ProdutoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/produtos")
@Api(tags = "Controller de Produtos")
public class ProdutoController {

	private ProdutoRepository produtoRepository;

	public ProdutoController(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}

	@ApiOperation("Criar um novo Produto")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
	}

	@ApiOperation("Atualizar um Produto por sua ID")
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
		produtoRepository.findById(id).map(p -> {
			produto.setId(p.getId());
//			produto.setDescricao(p.getDescricao());
//			produto.setPreco(p.getPreco());
			produtoRepository.save(produto);
			return produto;
		}).orElseThrow(() -> new ProdutoNaoEncontradoException());
	}

	@ApiOperation("Deletar um Produto por sua ID")
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtoRepository.findById(id).map(p -> {
			produtoRepository.delete(p);
			return Void.TYPE;
		}).orElseThrow(() -> new ProdutoNaoEncontradoException());
	}

	@ApiOperation("Buscar um Produto por sua ID")	
	@GetMapping("{id}")
	public Produto getById(@PathVariable Integer id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ProdutoNaoEncontradoException());
	}
	@ApiOperation("Buscar todos os Produtos")
	@GetMapping
	public List<Produto> findAll (Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Produto> example = Example.of(filtro, matcher);
		return produtoRepository.findAll(example);
	}
}
