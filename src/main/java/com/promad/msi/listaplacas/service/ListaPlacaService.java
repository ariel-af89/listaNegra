package com.promad.msi.listaplacas.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.promad.msi.listaplacas.cliente.ListaPlacas;
import com.promad.msi.listaplacas.helper.PlacasHelper;
import com.promad.msi.listaplacas.model.RegistroModel;

import lombok.extern.java.Log;

@Service
@Log
public class ListaPlacaService {
	
	@Autowired
	public ListaPlacas listaPlacas;
	
	@Value("${autenticacion.token}")
	private String token;
	
	@Value("${url.save}")
	private String save;
	
	@Value("${alta.usuario}")
	private String usuarioAlta;
	
	public Object savePlaca(RegistroModel registroModel) {
	
		registroModel.getIncidenteModel().setUsuario(usuarioAlta);
		
		URI baseUrl = URI.create(save);
		return listaPlacas.savePlaca(baseUrl,token,PlacasHelper.domainToRepository(registroModel));
		
	}
	
}
