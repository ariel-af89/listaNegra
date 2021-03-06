package com.promad.msi.listaplacas.errorhandling;

import java.net.URI;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.stereotype.Component;

import com.promad.msi.listaplacas.cliente.ListaPlacas;
import com.promad.msi.listaplacas.helper.PlacasHelper;
import com.promad.msi.listaplacas.model.RegistroModel;
import com.promad.msi.listaplacas.service.ListaPlacaService;
import com.promad.msi.listaplacas.utilities.JSONManager;


@Component
public class PlacaRegisterRetryListener  extends RetryListenerSupport {
	private final static Log LOG= LogFactory.getLog(PlacaRegisterRetryListener.class);
	private final int ATTEMPTS=3;
	@Autowired
	public ListaPlacas listaPlacas;
	
	
	@Value("${autenticacion.token}")
	private String token;
	
	@Value("${url.save}")
	private String save;

	@Override
	public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
		LOG.info("********* Se reintenta enviar la placa: "+ListaPlacaService.getCurrentRegistroModel().getVehiculoInvolucradoModel().getPlaca()+", numero de intento: "+context.getRetryCount()+" *********");
	    super.onError(context, callback, throwable);
	}
	
	@Override
	public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
		if (context.getRetryCount() == ATTEMPTS) {

			if (ListaPlacaService.getCurrentRegistroModel() != null) {
				try {
					JSONManager.saveToJsonArray(ListaPlacaService.getCurrentRegistroModel());
				} catch (Exception e) {
					LOG.error(e.getMessage() + " Se envio: " + ListaPlacaService.getCurrentRegistroModel());
				}
			}
		} else {
			LOG.info("Get JSON");
			JSONArray registrosList= JSONManager.getArrayJSON();
			System.out.println("IMPRIME REGISTROS -->" + registrosList);
			boolean inserted;
			URI baseUrl = URI.create(save);
			if (registrosList.size()>0){
				inserted = false;
				try {
				registrosList.forEach(reg -> {
					String json = reg.toString();
					Gson gson = new Gson();
					RegistroModel rm = gson.fromJson(json, RegistroModel.class);
					RegistroModel mov = rm;
					System.out.println(reg);

					LOG.info("*********Se envia el registro pendiente de la placa " + rm.getVehiculoInvolucradoModel().getPlaca() + " *********");
					listaPlacas.savePlaca(baseUrl, token, PlacasHelper.domainToRepository(rm));

				});
					inserted = true;
				} catch (Exception ex) {
					inserted = false;
				} /*finally {
					if (inserted) {
						registrosList.remove();
					}
				}*/

			}

		}

		super.close(context, callback, throwable);
	}
	
}