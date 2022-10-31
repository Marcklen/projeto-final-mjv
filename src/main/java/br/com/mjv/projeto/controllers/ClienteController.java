package br.com.mjv.projeto.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.mjv.projeto.entities.Cliente;
import br.com.mjv.projeto.repositories.ClienteRepository;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;

	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clienteRepository.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			clienteRepository.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente) {
		return clienteRepository.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clienteRepository.save(cliente);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/listar")
	public ResponseEntity find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		List<Cliente> lista = clienteRepository.findAll(example);
		return ResponseEntity.ok(lista);
	}
}