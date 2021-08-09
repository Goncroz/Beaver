package com.beaver.beaver.Seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.beaver.beaver.model.Cliente;
import com.beaver.beaver.repository.ClienteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String nomeCliente) throws UsernameNotFoundException 
	{
		// TODO Auto-generated method stub
		Optional<Cliente> cliente = clienteRepository.findByNomeClienteContainingIgnoreCase(nomeCliente);
		cliente.orElseThrow(() -> new UsernameNotFoundException(cliente + "not found"));
		return cliente.map(UserDetailsImpl::new).get();
	}

}
