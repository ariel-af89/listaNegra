package com.promad.msi.listaplacas.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Incidente {
	
	@JsonProperty("Folio")
	private String Folio;
	private int idMotivo;
	private Integer idOrigen;
	@JsonProperty("AveriguacionPrevia")
	private String AveriguacionPrevia;
	@JsonProperty("Observaciones")
	private String Observaciones;
	@Override
	public String toString() {
		return "Incidente [Folio=" + Folio + ", idMotivo=" + idMotivo + ", idOrigen=" + idOrigen
				+ ", AveriguacionPrevia=" + AveriguacionPrevia + ", Observaciones=" + Observaciones + "]";
	}
	
	

}
