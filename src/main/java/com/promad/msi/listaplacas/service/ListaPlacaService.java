package com.promad.msi.listaplacas.service;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.promad.msi.listaplacas.cliente.ListaPlacas;
import com.promad.msi.listaplacas.helper.PlacasHelper;
import com.promad.msi.listaplacas.model.RegistroModel;

import lombok.extern.java.Log;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@Log
public class ListaPlacaService {
	
	@Autowired
	public ListaPlacas listaPlacas;
	
	@Value("${autenticacion.token}")
	private String token;
	
	@Value("${url.save}")
	private String save;
	
	@Value("${alta.usuario}")
	private String usuarioAlta;
	
	public Object savePlaca(RegistroModel registroModel) {
		
		System.out.println("se recibe: "+ registroModel.toString());
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, "{\"Usuario\":\"ARIEL\",\"VehiculoInvolucrado\":{\"idMarca\":10,\"idSubMarca\":5,\"idTipo\":20,\"idProcedencia\":16,\"Placa\":\"123ABC\",\"Modelo\":2018,\"Color\":\"ROJO\",\"TipoLista\":1},\"Incidente\":{\"idMotivo\":4,\"idOrigen\":5,\"Folio\":\"C5/20191125/01245\",\"AveriguacionPrevia\":\"C5CDMX/PGJ/0001/20191011\",\"Observaciones\":\"string\"}}");
				Request request = new Request.Builder()
				  .url("http://43306fc1.ngrok.io/WebServices/WsListaNegra/Placas.php")
				  .method("POST", body)
				  .addHeader("Authorization", "Bearer UHIwbTRkOnp2c2ppVlV0dVRPSUFFUUZISzdsa0E9PQ==")
				  .addHeader("Content-Type", "application/json")
				  .build();
				try {
					Response response = client.newCall(request).execute();
					System.out.println("Respuesta C5 Postman:" +  response.message()+" codigo "+ response.code());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error en Cliente Postman:" + e.getMessage());
					e.printStackTrace();
				}
		
//		URI baseUrl = URI.create(save);
		//return listaPlacas.savePlaca(baseUrl,token,PlacasHelper.domainToRepository(registroModel));
		return new Object();
	}
	
}
