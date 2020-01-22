package com.promad.msi.listaplacas.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.promad.msi.listaplacas.errorhandling.ErrorDescriptor;
import com.promad.msi.listaplacas.model.RegistroModel;
import com.promad.msi.listaplacas.model.RespuestaModel;
import com.promad.msi.listaplacas.service.ListaPlacaService;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/V1")
@Api(value = "Alta placas", description = "Dar de alta una placa" )
public class ListaPlacasController {
	
	@Autowired
	ListaPlacaService listaPlacaService;
	
	@ApiOperation(value = "Agrega placa a lista", produces="application/json" )
    @RequestMapping(value = "/placas", method = RequestMethod.POST )
	@ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creado", response = RespuestaModel.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorDescriptor.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorDescriptor.class)
    })
   	public RespuestaModel registraPlaca(@RequestBody @Valid RegistroModel registroModel){
		
   		return listaPlacaService.pruebaGet(registroModel);
   	}

}
