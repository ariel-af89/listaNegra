package com.promad.msi.listaplacas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promad.msi.listaplacas.cliente.ListaPlacas;
import com.promad.msi.listaplacas.helper.PlacasHelper;
import com.promad.msi.listaplacas.model.RegistroModel;
import com.promad.msi.listaplacas.model.RespuestaModel;
import com.promad.msi.listaplacas.model.RespuestaWService;

@Service
public class ListaPlacaService {
	
	@Autowired
	public ListaPlacas listaPlacas;
	
	public Object savePlaca(RegistroModel registroModel) {
		
		return listaPlacas.savePlaca(registroModel.getToken(),PlacasHelper.domainToRepository(registroModel));
		
	}
	
}
