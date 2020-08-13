package com.promad.msi.listaplacas.controller;


import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.promad.msi.listaplacas.errorhandling.ErrorDescriptor;
import com.promad.msi.listaplacas.model.EventoModel;
import com.promad.msi.listaplacas.model.RegistroModel;
import com.promad.msi.listaplacas.model.RespuestaModel;
import com.promad.msi.listaplacas.model.RespuestaWService;
import com.promad.msi.listaplacas.service.EventoService;
import com.promad.msi.listaplacas.service.ListaPlacaService;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/placa")
@Api(value = "Alta placas", description = "Dar de alta una placa" )
@CrossOrigin()
public class ListaPlacasController {
	
	@Autowired
	ListaPlacaService listaPlacaService;
	
	@Autowired
	EventoService eventoService;
	
	@ApiOperation(value = "Agrega placa a lista", produces="application/json" )
    @RequestMapping(value = "/placas", method = RequestMethod.POST )
	@ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creado", response = RespuestaWService.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDescriptor.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorDescriptor.class)
    })
   	public Object registraPlaca(@RequestBody @Valid RegistroModel registroModel) throws URISyntaxException{
		
   		return listaPlacaService.savePlaca(registroModel);
   	}
	
	@ApiOperation(value = "Registra un evento", produces="application/json" )
    @RequestMapping(value = "/evento", method = RequestMethod.POST )
	@ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creado", response = RespuestaModel.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDescriptor.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorDescriptor.class)
    })
   	public Object creaEvento(@RequestBody @Valid EventoModel eventoModel) throws JsonProcessingException{
		
   		return eventoService.creaEvento(eventoModel);
   	}
	
	

    @RequestMapping(value = "/prueba", method = RequestMethod.POST )   
   	public void creaEvento(@RequestBody @Valid RegistroModel registroModel) throws JsonProcessingException{
		
   	   eventoService.creaPrueba(registroModel);
   	}


}
