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

import br.com.mjv.projeto.entities.Cliente;
import br.com.mjv.projeto.exceptions.ClienteNaoEncontradoException;
import br.com.mjv.projeto.repositories.ClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/clientes")
@Api(tags = "Controller de Clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;

	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@ApiOperation("Buscar um Cliente por sua ID")
	@GetMapping("{id}")
	public Cliente getClienteById(@PathVariable Integer id) {
		return clienteRepository
				.findById(id)
				.orElseThrow(() -> new ClienteNaoEncontradoException());
	}

	@ApiOperation("Criar um novo Cliente")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@ApiOperation("Deletar um Cliente por sua ID")
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		clienteRepository.findById(id).map(clienteExistente -> {
			clienteRepository.delete(clienteExistente);
			return clienteExistente;
		}).orElseThrow(() -> new ClienteNaoEncontradoException());
		
	}

	@ApiOperation("Atualizar um Cliente por sua ID")
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
		clienteRepository.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clienteRepository.save(cliente);
			return clienteExistente;
		}).orElseThrow(() -> new ClienteNaoEncontradoException());
	}

	@ApiOperation("Buscar todos os Clientes")
	@GetMapping
	public List<Cliente> findAll (Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Cliente> example = Example.of(filtro, matcher);
		return clienteRepository.findAll(example);
	}
}