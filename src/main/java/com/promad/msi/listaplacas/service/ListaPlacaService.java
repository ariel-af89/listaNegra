package com.promad.msi.listaplacas.service;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.promad.msi.listaplacas.cliente.ListaPlacas;
import com.promad.msi.listaplacas.errorhandling.PlacaRegisterRetryListener;
import com.promad.msi.listaplacas.helper.PlacasHelper;
import com.promad.msi.listaplacas.model.RegistroModel;

import lombok.extern.java.Log;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@Log
public class ListaPlacaService {
	
	@Autowired
	public ListaPlacas listaPlacas;
	
	@Autowired
    private PlacaRegisterRetryListener placaRegisterRetryListener;
	
	@Value("${autenticacion.token}")
	private String token;
	
	@Value("${url.save}")
	private String save;
	
	@Value("${alta.usuario}")
	private String usuarioAlta;
	
private static RegistroModel currentRegistroModel;
	
	@Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(10000), listeners = "placaRegisterRetryListener")
	public Object savePlaca(RegistroModel registroModel) {		
		System.out.println("se recibe: "+ registroModel.toString());	
		this.currentRegistroModel= registroModel;
		URI baseUrl = URI.create(save);
		return listaPlacas.savePlaca(baseUrl,token,PlacasHelper.domainToRepository(registroModel));
	}
	
	@Recover
    public Object retryExampleRecovery(Exception t, RegistroModel registroModel) {
    	//JSONManager.saveToJsonFile(registroModel);
        return "Retry Recovery OK!";
    }

	public static RegistroModel getCurrentRegistroModel() {
		return currentRegistroModel;
	}

	public static void setCurrentRegistroModel(RegistroModel currentRegistroModel) {
		ListaPlacaService.currentRegistroModel = currentRegistroModel;
	}
	
}
