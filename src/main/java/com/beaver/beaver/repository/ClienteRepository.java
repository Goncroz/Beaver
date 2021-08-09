package com.beaver.beaver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beaver.beaver.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>
{
	public Optional<Cliente> findByNomeClienteContainingIgnoreCase (String nomeCliente);

}
