package com.promad.msi.listaplacas.cliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClienteWebGenerico {

	private Logger logger = LoggerFactory.getLogger(ClienteWebGenerico.class);
 
	private final WebClient.Builder webClientBuilder; 

	private final int msDataQServiceTimeoutSec;
	private WebClient webClient;
	
	public ClienteWebGenerico(WebClient.Builder webClientBuilder,
            @Value("30") int msDataQServiceTimeoutSec  ){
		this.webClientBuilder = webClientBuilder; 
		this.msDataQServiceTimeoutSec=msDataQServiceTimeoutSec;
	} 
	
	public String getRequestDataVariable(String parametrosData, String uri  ){	
		
		   Mono<String> respuestaMono = getWebClient().post().uri(uri)
  		//.header("jsonDatos", parametrosData.toString()) 
  		.contentType(MediaType.APPLICATION_JSON_UTF8)
  		.accept(MediaType.APPLICATION_JSON_UTF8)
  		.syncBody(parametrosData)		
          .retrieve().bodyToMono(String.class)
           .log()
          .onErrorMap(WebClientResponseException.class, ex -> handleException(ex))
          .timeout(Duration.ofSeconds(msDataQServiceTimeoutSec));		
		    
		 return respuestaMono.block();  
	}
	
	
	
	
	private WebClient getWebClient() {
        if (webClient == null) {
            webClient = webClientBuilder.build();
        }
        return webClient;
    }
    private Throwable handleException(Throwable ex) {

        if (!(ex instanceof WebClientResponseException)) {
     	   logger.warn("Got a unexpected error: {}, will rethrow it", ex.toString());
            return ex;
        }

        WebClientResponseException wcre = (WebClientResponseException)ex;

        switch (wcre.getStatusCode()) {

        case NOT_FOUND:
            //return new Exception(getErrorMessage(wcre));

        case UNPROCESSABLE_ENTITY :
            //return new Exception(getErrorMessage(wcre));

        default:
     	   logger.warn("Got a unexpected HTTP error: {}, will rethrow it", wcre.getStatusCode());
     	   logger.warn("Error body: {}", wcre.getResponseBodyAsString());
            return ex;
        }
    }
	 

}

