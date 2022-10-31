package br.com.mjv.projeto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mjv.projeto.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	
//	@Query(value = "select * from cliente c where c.nome like '%:nome%' ")
	List<Cliente> findByNome(@Param("nome") String nome); //@Param("nome")
	
//	@Query(value = "delete from cliente c where c.nome =:nome ")
	@Modifying
	void deleteByNome(String nome);
	
	boolean existsByNome(String nome);
	
	@Query("select c from cliente c left join fetch c.pedidos where c.id = :id")
	Cliente findClienteFetchPedidos (@Param("id") Integer id);
}
