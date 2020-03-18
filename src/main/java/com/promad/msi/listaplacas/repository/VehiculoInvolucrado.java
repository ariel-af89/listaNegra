package com.promad.msi.listaplacas.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VehiculoInvolucrado {
	
	@JsonProperty("Placa")
	private String Placa;
	private int idMarca;
	private int idSubMarca;
	@JsonProperty("Modelo")
	private int Modelo;
	private int idTipo;
	@JsonProperty("Color")
	private String Color;
	private int idProcedencia;
	@JsonProperty("TipoLista")
	private int TipoLista;

}
