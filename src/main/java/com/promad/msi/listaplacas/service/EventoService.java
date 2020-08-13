package com.promad.msi.listaplacas.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.promad.msi.listaplacas.cliente.EventoPlaca;
import com.promad.msi.listaplacas.helper.PlacasHelper;
import com.promad.msi.listaplacas.model.EventoModel;
import com.promad.msi.listaplacas.model.RegistroModel;

import lombok.extern.java.Log;
import reactor.core.publisher.Mono;



@Service
@Log
public class EventoService {

	private final WebClient.Builder webClientBuilder; 
	private final int msDataQServiceTimeoutSec;
	private WebClient webClient;
	
	
	public EventoService(WebClient.Builder webClientBuilder,
            @Value("30") int msDataQServiceTimeoutSec  ){
		this.webClientBuilder = webClientBuilder; 
		this.msDataQServiceTimeoutSec=msDataQServiceTimeoutSec;
	} 
	 
	
	@Autowired
	EventoPlaca eventoPlaca;
	
	@Value("${url.evento}")
	private String evento;
	
	public Object creaEvento(EventoModel eventoModel) throws JsonProcessingException {

		URI baseUrl = URI.create(evento);
		
		return eventoPlaca.guardaEvento(baseUrl,PlacasHelper.domainToRepository(eventoModel));
		
	}

	public void creaPrueba(@Valid RegistroModel registroModel) {
		String url="http://43306fc1.ngrok.io/WebServices/WsListaNegra/Placas.php";
		
		String resultadoJson="ok";
		log.info("::::::::::::::::::::::::::::::::invocarServicioExterno   url::::::"+ url);
		log.info("::::::::::::::::::::::::::::::::invocarServicioExterno   datos ::::::"+new Gson().toJson(PlacasHelper.domainToRepository(registroModel)));
		
		
        Mono<Object> resultado = null;
		try {
			Gson gson=new Gson();	
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.put("API-Version", Arrays.asList("41"));
			
			String authHeader = "Basic " +  new String("UHIwbTRkOnp2c2ppVlV0dVRPSUFFUUZISzdsa0E9PQ==");  // new String(encodedAuth);
			headers.put("Authorization", Arrays.asList(authHeader));
			

			String recordIDs=gson.toJson(PlacasHelper.domainToRepository(registroModel));

			
			log.info("Cadena json:::: "+recordIDs);
			
			HttpEntity<String> request = new HttpEntity<String>(recordIDs, headers);

		
	     ResponseEntity<Object> restTemplate = new RestTemplate().exchange(url, HttpMethod.POST, request, Object.class);	
			
			log.info("URL::" +restTemplate.getStatusCodeValue());

			log.info("URL::" +restTemplate.getStatusCode());
			
			log.info("URL::" +restTemplate.getBody().toString());
			log.info(restTemplate.getBody().toString());
			
			
		
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 			 
		log.info("::::::::::::::::::::::::::::::::: :: resultadoEnvio a centro " +  resultado.block() );
		
		
		
	
	
	}
	

	 

}
