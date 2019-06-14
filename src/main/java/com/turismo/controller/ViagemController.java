package com.turismo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.turismo.model.Viagem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turismo.repository.ViagemRepository;

import com.turismo.util.WriteFile;


@RestController
public class ViagemController {
	
	@Autowired
	ViagemRepository repository;
	
	//http://localhost:8080/viagem/save
	/*
	 * INSERIR
	   {
	    "origem": {
		    "id": 5,
		    "descricao": "Praia",
		    "diaria": 45
		},
		"destino": {
		    "id": 6,
		    "descricao": "Fazenda",
		    "diaria": 30
		},
	    "saida": "01/07/2019",
	    "volta": "10/07/2019"
	   }
	 * 
	 * ALTERAR
	   {
	    "id": 1,
	    "origem": {
		    "id": 5,
		    "descricao": "Praia",
		    "diaria": 45
		},
		"destino": {
		    "id": 6,
		    "descricao": "Fazenda",
		    "diaria": 30
		},
	    "saida": "01/07/2019",
	    "volta": "10/07/2019"
	   }
	 * */
	@PostMapping("viagem/save")
	public Viagem save(@RequestBody Viagem viagem){
		return repository.save(viagem);
	}
	
	//http://localhost:8080/viagem/all
	@GetMapping("viagem/all")
	public List<Viagem> all(){
		return (List<Viagem>) repository.findAll();
	}
	
	//http://localhost:8080/viagem/file
	@GetMapping("viagem/file")
	public String createFile(){
		String arquivo = "/home/gui/Documentos/Faculdade/SD/turismoSpring/relatorio.txt";
		
		WriteFile editorArquivo = new WriteFile(arquivo, true);
		
		try {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			String dataAtual = formatoData.format(new Date());
			
			editorArquivo.writeToFile("Relatório gerado em " + dataAtual);
			editorArquivo.writeToFile("id;origem;destino;valorTotal");
			
			List<Viagem> viagens = repository.findAll();
			
			for (Viagem viagem : viagens) {
				editorArquivo.writeToFile(String.valueOf(viagem.getId())+";"+viagem.getOrigem().getDescricao()+";"+viagem.getDestino().getDescricao()+";"+String.valueOf(viagem.getValorTotal()));
			}
		} catch (IOException e) {
			return "Falha ao gerar o arquivo";
		}
		
		return "OK";
	}
	
	//http://localhost:8080/viagem/delete/2
	@DeleteMapping("viagem/delete/{id}")
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