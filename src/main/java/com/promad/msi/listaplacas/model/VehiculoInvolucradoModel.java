package com.promad.msi.listaplacas.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Vehiculo Involucrado")
@Data
public class VehiculoInvolucradoModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7517834518371754134L;

	@NotNull(message = "no puede ser nulo" )// 
	//@Pattern(regexp ="^(([A-Z0-9]{5,6})|(([A-Z0-9]{5}-([A-Z0-9]{2}))|([A-Z0-9]{3}-([A-Z0-9]{1}))|([A-Z0-9]{1,3}-[A-Z0-9]{2,5}))|((([A-Z0-9]{1}-[A-Z0-9]{2,5}-[A-Z0-9]{1,3})|([A-Z0-9]{2}-[A-Z0-9]{1,4}-[A-Z0-9]{1,3})|([A-Z0-9]{3}-[A-Z0-9]{2,3}-[A-Z0-9]{1,3}))))$", message = "No es un formato de placa válido" )
	@ApiModelProperty(position = 1, required = true,  example = "123ABC", value = "Placa del vehículo (sin caracteres extraños o espacios)", name="Placa")
	private String placa;
	
	@ApiModelProperty(position = 2, required = false,  example = "10", value = "Correspondiente a la Marca del vehículo (Catálogo)", name="id Marca")
	private int idMarca;
	
	@ApiModelProperty(position = 3, required = false,  example = "5", value = "Corresponde a la Submarca (Catálogo)", name="id SubMarca")
	private int idSubMarca;
	
	@ApiModelProperty(position = 4, required = false,  example = "2018", value = "Corresponderá al año del vehículo", name="modelo")
	private int modelo;
	
	@ApiModelProperty(position = 5, required = false,  example = "20", value = "Corresponde al tipo de vehículo (Catálogo)", name="id Tipo")
	private int idTipo;
	
	@ApiModelProperty(position = 6, required = false,  example = "1", value = "Color del vehículo", name="Color")
	@NumberFormat
	private String color;
	
	@ApiModelProperty(position = 7, required = false,  example = "16", value = "Corresponde a la Ciudad de origen del vehículo (Catálogo)", name="id Procedencia")
	private int idProcedencia;
	
	@ApiModelProperty(position = 8, required = false,  example = "5", value = "Origen del reporte (Catálogo)", name="Origen")
	private Integer origen;
	
	@NotNull(message = "no puede ser nulo" )// 
	@ApiModelProperty(position = 9, required = false,  example = "1", value = "Tipo de lista a la cual pertenecerá el vehículo* (Catálogo)", name="Tipo de Lista")
	private Integer tipoLista;
}
