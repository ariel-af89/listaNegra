package com.promad.msi.listaplacas.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EventoRepository {
	
	@JsonProperty("Usuario")
	private String Usuario;
	@JsonProperty("VehiculoInvolucrado")
	private VehiculoInvolucrado VehiculoInvolucrado;
	@JsonProperty("Incidente")
	private Incidente Incidente;

}
