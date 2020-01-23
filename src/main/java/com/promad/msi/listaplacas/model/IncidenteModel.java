package com.promad.msi.listaplacas.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Incidente")
@Data
public class IncidenteModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6956344083803267381L;
	
	@NotNull(message = "no puede ser nulo" )
	@ApiModelProperty(position = 1, required = true,  example = "C5/20191125/01245", value = "Número de folio generado en el sistema CAD", name="Folio")
	private String folio;
	
	@NotNull(message = "no puede ser nulo" )
	@ApiModelProperty(position = 2, required = false, value = "Motivo del incidente (Catálogo)", example = "4", name = "Motivo" )
	private String motivo;
	
	@NotNull(message = "no puede ser nulo" )
	@ApiModelProperty(position = 3, required = false, value = "Usuario que realizó el registro de la placa en la lista.", example = "C5952123", name="Usuario")
	private String usuario;

}
