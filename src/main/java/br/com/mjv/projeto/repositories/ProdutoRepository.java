package br.com.mjv.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mjv.projeto.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
