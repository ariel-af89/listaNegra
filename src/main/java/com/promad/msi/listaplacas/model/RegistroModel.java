package com.promad.msi.listaplacas.model;


import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
	
	@ApiModelProperty(position = 3, required = true, dataType = "string" )
	private String averiguacionPrevia;
	
	@ApiModelProperty(position = 4, required = true, dataType = "string" )
	private String observaciones;
	
	@NotNull(message = "no puede ser nulo")
	@ApiModelProperty(position = 5, required = true, value = "tokrn", dataType = "String")
	private String token;
	

}
