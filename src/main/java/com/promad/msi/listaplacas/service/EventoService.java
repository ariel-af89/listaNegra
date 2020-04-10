package com.promad.msi.listaplacas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.promad.msi.listaplacas.cliente.EventoPlaca;
import com.promad.msi.listaplacas.helper.PlacasHelper;
import com.promad.msi.listaplacas.model.EventoModel;



@Service
public class EventoService {
	
	
	@Autowired
	EventoPlaca eventoPlaca;
	
	public Object creaEvento(EventoModel eventoModel) throws JsonProcessingException {


		return eventoPlaca.guardaEvento(PlacasHelper.domainToRepository(eventoModel));
		
	}

}
