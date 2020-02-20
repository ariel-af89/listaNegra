package com.promad.msi.listaplacas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.promad.msi.listaplacas.cliente.ClienteWebGenerico;
import com.promad.msi.listaplacas.model.EventoModel;



@Service
public class EventoService {
	
	@Autowired
	ClienteWebGenerico clienteWebGenerico;
	
	public Object creaEvento(EventoModel eventoModel) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		//origen
		//image
		// Java object to JSON string
		String jsonString = mapper.writeValueAsString(eventoModel);
		
		String origenes = clienteWebGenerico.getRequestDataVariable(jsonString, "http://52.9.236.138:9287/api/emergenciasExternas/saveEventoExterno");
		
		JsonObject convertedObject = new Gson().fromJson(origenes, JsonObject.class);
		
		Gson gson=new Gson();
		Object catOrigenLlamadaModel = gson.fromJson(convertedObject, Object.class);
		
		
		System.out.println("Origenes: " + catOrigenLlamadaModel);

		
		return null;
	}

}
