package com.promad.msi.listaplacas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.promad.msi.listaplacas.cliente.ListaPlacas;
import com.promad.msi.listaplacas.helper.PlacasHelper;
import com.promad.msi.listaplacas.model.RegistroModel;

@Service
public class ListaPlacaService {
	
	@Autowired
	public ListaPlacas listaPlacas;
	
	@Value("${autenticacion.token}")
	private String token;
	
	public Object savePlaca(RegistroModel registroModel) {
		
		return listaPlacas.savePlaca(token,PlacasHelper.domainToRepository(registroModel));
		
	}
	
}
