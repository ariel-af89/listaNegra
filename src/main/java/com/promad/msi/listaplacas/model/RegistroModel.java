package com.promad.msi.listaplacas.model;


import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import org.springframework.beans.factory.annotation.Value;

@ApiModel(value = "Registro")
@Data
public class RegistroModel implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 3386811806405625264L;
	
	@ApiModelProperty(position = 1, required = true, dataType = "Incidente", value = "incidenteModel"  )
	@NotNull
	@Valid
	private IncidenteModel incidenteModel;
	
	@ApiModelProperty(position = 2, required = true, dataType = "VehiculoInvolucradoModel", value = "vehiculoInvolucradoModel" )
	@NotNull
	@Valid
	private VehiculoInvolucradoModel vehiculoInvolucradoModel;
	
	@ApiModelProperty(position = 3, required = true, dataType = "string", example = "C5CDMX/PGJ/0001/20191011" )
	private String averiguacionPrevia;
	
	@ApiModelProperty(position = 4, required = true, dataType = "string" )
	private String observaciones;
	

}
