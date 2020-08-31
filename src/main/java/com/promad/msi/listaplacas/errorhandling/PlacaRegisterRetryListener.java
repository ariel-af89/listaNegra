package com.promad.msi.listaplacas.errorhandling;

import java.net.URI;

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
		if(context.getRetryCount()==ATTEMPTS) {
			
			if(ListaPlacaService.getCurrentRegistroModel()!=null) {
				LOG.info("Save to JSON placa");
				JSONManager.saveToJsonFile(ListaPlacaService.getCurrentRegistroModel());
			}
		}else { 
			LOG.info("Get JSON");
			JSONArray ja= JSONManager.getArrayFromJsonFile();
			boolean inserted;
			URI baseUrl = URI.create(save);
			for(int i=0; i< ja.size() ; i++) {					
				inserted=false;
				try	{
					RegistroModel rm = JSONManager.parseregistroObject((JSONObject) ja.get(i));
					listaPlacas.savePlaca(baseUrl,token,PlacasHelper.domainToRepository(rm));
					
					LOG.info("*********Se envia el registro pendiente de la placa "+rm.getVehiculoInvolucradoModel().getPlaca()+" *********");
					inserted = true;
				} catch(Exception ex) {
					inserted = false;
				} finally {
					if(inserted) {
						ja.remove(i);
						i--;
					} 
				}
			}
			JSONManager.updateJsonFile(ja.toJSONString());
		}
	    super.close(context, callback, throwable);
	}
	
	
}