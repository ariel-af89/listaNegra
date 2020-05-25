package com.promad.msi.listaplacas.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.promad.msi.listaplacas.cliente.EventoPlaca;
import com.promad.msi.listaplacas.helper.PlacasHelper;
import com.promad.msi.listaplacas.model.EventoModel;

import lombok.extern.java.Log;



@Service
@Log
public class EventoService {
	
	
	@Autowired
	EventoPlaca eventoPlaca;
	
	@Value("${url.evento}")
	private String evento;
	
	
	public Object creaEvento(EventoModel eventoModel) throws JsonProcessingException {

		URI baseUrl = URI.create(evento);
		
		System.out.println("evento ::::::::::::::::::::::::::: " + evento);
		System.out.println("url ::::::::::::::::::::::::::::::::::::::::::: " + baseUrl);
		
		return eventoPlaca.guardaEvento(baseUrl,PlacasHelper.domainToRepository(eventoModel));
		
	}

}
