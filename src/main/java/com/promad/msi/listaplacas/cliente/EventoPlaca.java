package com.promad.msi.listaplacas.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.promad.msi.listaplacas.model.EventoPeticion;

@FeignClient(value = "eventoPlaca", url = "${url.evento}")
public interface EventoPlaca {
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveEventoExternoLPR", produces = "application/json")
	public Object guardaEvento(@RequestBody EventoPeticion eventoPeticion);

}
