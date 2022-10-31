package br.com.mjv.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mjv.projeto.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
