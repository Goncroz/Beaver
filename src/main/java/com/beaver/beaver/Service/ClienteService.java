package com.beaver.beaver.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.beaver.beaver.model.Cliente;
import com.beaver.beaver.model.ClienteLogin;
import com.beaver.beaver.repository.ClienteRepository;

@Service
public class ClienteService 
{
	@Autowired
	private ClienteRepository repository;
	
	public Cliente CadastrarCliente(Cliente cliente)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(cliente.getSenha());
		cliente.setSenha(senhaEncoder);
		
		return repository.save(cliente);
	}
	
	public Optional<ClienteLogin> Logar(Optional<ClienteLogin> user)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Cliente> cliente = repository.findByNomeClienteContainingIgnoreCase(user.get().getNomeCliente());
		
		if(cliente.isPresent())
		{
			if(encoder.matches(user.get().getNomeCliente(), cliente.get().getSenha()))
			{
				String auth = user.get().getNomeCliente() + ":" + user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);
				
				user.get().setToken(authHeader);
				
				return user;
			}
		}
		return null;
		
	}

}
