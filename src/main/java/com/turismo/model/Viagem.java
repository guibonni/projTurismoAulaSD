package com.turismo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="viagem")
public class Viagem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Local origem;
	
	@ManyToOne
	private Local destino;
	
	private String saida;
	
	private String volta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Local getOrigem() {
		return origem;
	}

	public void setOrigem(Local origem) {
		this.origem = origem;
	}

	public Local getDestino() {
		return destino;
	}

	public void setDestino(Local destino) {
		this.destino = destino;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public String getVolta() {
		return volta;
	}

	public void setVolta(String volta) {
		this.volta = volta;
	}
	
	private long getDiasDeViagem() {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date inicio = formatoData.parse(this.saida);
			Date fim = formatoData.parse(this.volta);
			
			long dias = fim.getTime() - inicio.getTime();
			
			return (TimeUnit.DAYS.convert(dias, TimeUnit.MILLISECONDS));
		} catch(ParseException e) {
			return 0;
		}
	}
	
	public double getValorTotal() {
		long dias = this.getDiasDeViagem();
		
		return (this.getDestino().getDiaria() * dias);
	}
	
}