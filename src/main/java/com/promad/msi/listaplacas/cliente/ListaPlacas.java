package com.promad.msi.listaplacas.cliente;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.promad.msi.listaplacas.model.RespuestaWService;
import com.promad.msi.listaplacas.repository.EventoRepository;

@FeignClient(value = "listaPlacas", url = "http://43306fc1.ngrok.io/WebServices/WsListaNegra")
public interface ListaPlacas {
	
	@RequestMapping(method = RequestMethod.POST, value = "/Placas.php", produces = "application/json")
	public Object savePlaca(@RequestHeader("Authorization") String bearerToken, @RequestBody EventoRepository eventoRepository);

	
}
