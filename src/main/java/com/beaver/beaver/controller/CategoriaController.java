package com.beaver.beaver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beaver.beaver.model.Categoria;
import com.beaver.beaver.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController 
{
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<com.beaver.beaver.model.Categoria>> GetAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	

}
