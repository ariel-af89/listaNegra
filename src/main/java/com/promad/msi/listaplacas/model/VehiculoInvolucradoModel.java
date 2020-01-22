package com.promad.msi.listaplacas.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
	@Pattern(regexp ="^((([A-Z0-9]{2,4}))-?(((([A-Z0-9]{2,4})-?)|(([A-Z0-9]{2}-[A-Z0-9]{2,3})))))$|(([0-9]{4}-[A-Z])$|(([0-9])-[A-Z]{2}-[0-9]{3}))$", message = "No es un formato de placa v�lido" )
	@ApiModelProperty(position = 1, required = true,  example = "123ABC", value = "Placa del veh�culo (sin caracteres extra�os o espacios)", name="Placa")
	private String placa;
	
	@ApiModelProperty(position = 2, required = false,  example = "10", value = "Correspondiente a la Marca del veh�culo (Cat�logo)", name="id Marca")
	private int idMarca;
	
	@ApiModelProperty(position = 3, required = false,  example = "5", value = "Corresponde a la Submarca (Cat�logo)", name="id SubMarca")
	private int idSubMarca;
	
	@ApiModelProperty(position = 4, required = false,  example = "2018", value = "Corresponder� al a�o del veh�culo", name="modelo")
	private String modelo;
	
	@ApiModelProperty(position = 5, required = false,  example = "20", value = "Corresponde al tipo de veh�culo (Cat�logo)", name="id Tipo")
	private int idTipo;
	
	@ApiModelProperty(position = 6, required = false,  example = "ROJO", value = "Color del veh�culo", name="Color")
	private String color;
	
	@ApiModelProperty(position = 7, required = false,  example = "16", value = "Corresponde a la Ciudad de origen del veh�culo (Cat�logo)", name="id Procedencia")
	private int idProcedencia;
	
	@ApiModelProperty(position = 8, required = false,  example = "5", value = "Origen del reporte (Cat�logo)", name="Origen")
	private int origen;
	
	@ApiModelProperty(position = 9, required = false,  example = "1", value = "Tipo de lista a la cual pertenecer� el veh�culo* (Cat�logo)", name="Tipo de Lista")
	private int tipoLista;
}
