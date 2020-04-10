package com.promad.msi.listaplacas.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Evento")
public class EventoModel {

	@ApiModelProperty(position = 1, required = true,  example = "00"  )
	@NotNull
	private String status;
	
	@ApiModelProperty(position = 2, required = true,  example = "Existe Registro."  )
	@NotNull
	private String descripcion;
	
	@ApiModelProperty(position = 3, required = true,  example = "123ABC"  )
	@Pattern(regexp ="^(([A-Z0-9]{5,6})|(([A-Z0-9]{5}-([A-Z0-9]{2}))|([A-Z0-9]{3}-([A-Z0-9]{1}))|([A-Z0-9]{1,3}-[A-Z0-9]{2,5}))|((([A-Z0-9]{1}-[A-Z0-9]{2,5}-[A-Z0-9]{1,3})|([A-Z0-9]{2}-[A-Z0-9]{1,4}-[A-Z0-9]{1,3})|([A-Z0-9]{3}-[A-Z0-9]{2,3}-[A-Z0-9]{1,3}))))$", message = "No es un formato de placa válido" )
	@NotNull
	private String placa;
	
	@ApiModelProperty(position = 4, required = false,  example = "10"  )
	private int idMarca;
	
	@ApiModelProperty(position = 5, required = false,  example = "5"  )
	private int idSubMarca;
	
	@ApiModelProperty(position = 6, required = false,  example = "1", value = "Color del vehículo", name="Color")
	private String color;
	
	@ApiModelProperty(position = 7, required = false,  example = "20", value = "Corresponde al tipo de vehículo (Catálogo)", name="id Tipo")
	private int idTipo;
	
	@ApiModelProperty(position = 8, required = false,  example = "2018", value = "Corresponderá al año del vehículo", name="modelo")
	private int modelo;
	
	@ApiModelProperty(position = 9, required = false,  example = "16", value = "Corresponde a la Ciudad de origen del vehículo (Catálogo)", name="id Procedencia")
	private int idProcedencia;
	
	@ApiModelProperty(position = 10, required = false,  example = "5", value = "Origen del reporte (Catálogo)", name="Origen")
	private int origen;
	
	@NotNull(message = "no puede ser nulo" )
	@ApiModelProperty(position = 11, required = true,  example = "C5/20191125/01245", value = "Número de folio generado en el sistema CAD", name="Folio")
	private String folio;
	
	@NotNull(message = "no puede ser nulo" )
	@ApiModelProperty(position = 12, required = false,  example = "1", value = "Tipo de lista a la cual pertenecerá el vehículo* (Catálogo)", name="Tipo de Lista")
	private int tipoLista;
	
	@ApiModelProperty(position = 13, required = false, dataType = "string", example = "C5CDMX/PGJ/0001/20191011" )
	private String averiguacionPrevia;

	@ApiModelProperty(position = 14, required = false, dataType = "string" )
	private String observaciones;
	
	@ApiModelProperty(position = 15, required = false, dataType = "string")
	private String placaB64;
	
	@ApiModelProperty(position = 16, required = true, dataType = "string", example = "1" )
	private String claveDispositivo;
}
