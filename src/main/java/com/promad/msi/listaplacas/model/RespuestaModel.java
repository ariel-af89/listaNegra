package com.promad.msi.listaplacas.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Respuesta")
@Data
public class RespuestaModel {
	
	@ApiModelProperty(position = 1, required = true,  example = "00", value = "El estatus de la respuesta", name="Estatus"  )
	private String estatus;
	@ApiModelProperty(position = 2, required = true,  example = "Registro en Lista Negra Satisfactorio", value = "Mensaje de operación",  name="Mensaje")
	private String mensaje;

}
