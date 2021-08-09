package com.beaver.beaver.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beaver.beaver.Service.ClienteService;
import com.beaver.beaver.model.Cliente;
import com.beaver.beaver.model.ClienteLogin;

@RestController
@RequestMapping("clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController 
{
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/logar")
	public ResponseEntity<ClienteLogin> Autentication(@RequestBody Optional<ClienteLogin> user)
	{
		return clienteService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Cliente> Post (@RequestBody Cliente cliente)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(clienteService.CadastrarCliente(cliente));
		
	}

}