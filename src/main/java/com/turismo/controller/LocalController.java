package com.turismo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.turismo.model.Local;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turismo.repository.LocalRepository;


@RestController
public class LocalController {
	
	@Autowired
	LocalRepository repository;
	
	//http://localhost:8080/local/save
	/*
	 * INSERIR
	   {
	    "descricao": "Praia",
	    "diaria": 45
	   }
	 * 
	 * ALTERAR
	   {
	    "id" : 1,
	    "descricao": "Praia",
	    "diaria": 45
	   }
	 * */

	@PostMapping("local/save")
	public Local save(@RequestBody Local local){
		return repository.save(local);
	}
	
	//http://localhost:8080/local/all
	@GetMapping("local/all")
	public List<Local> all(){
		return (List<Local>) repository.findAll();
	}
	
	//http://localhost:8080/local/delete/2
	@DeleteMapping("local/delete/{id}")
	public boolean delete(@PathVariable("id") int id) {
		try {
			repository.deleteById(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}