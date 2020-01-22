package com.promad.msi.listaplacas.service;

import org.springframework.stereotype.Service;

import com.promad.msi.listaplacas.model.RegistroModel;
import com.promad.msi.listaplacas.model.RespuestaModel;

@Service
public class ListaPlacaService {
	
	public RespuestaModel pruebaGet(RegistroModel registroModel) {
		
		RespuestaModel respuestaModel = new RespuestaModel();
		
		respuestaModel.setEstatus("00");
		respuestaModel.setMensaje("Registro en Lista " + registroModel.getVehiculoInvolucradoModel().getTipoLista() + " Satisfactorio");
		
		return respuestaModel;
	}

}
