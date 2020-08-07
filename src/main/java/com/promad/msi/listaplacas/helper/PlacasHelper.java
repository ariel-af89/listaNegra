package com.promad.msi.listaplacas.helper;

import com.promad.msi.listaplacas.model.EventoModel;
import com.promad.msi.listaplacas.model.EventoPeticion;
import com.promad.msi.listaplacas.model.RegistroModel;
import com.promad.msi.listaplacas.repository.EventoRepository;
import com.promad.msi.listaplacas.repository.Incidente;
import com.promad.msi.listaplacas.repository.VehiculoInvolucrado;

public class PlacasHelper {

	public static EventoRepository domainToRepository(RegistroModel registroModel) {
		
		EventoRepository eventoRepository = new EventoRepository();
		
		if(registroModel.getIncidenteModel()!=null && registroModel.getVehiculoInvolucradoModel() != null ) {
			
			//incidente
			Incidente incidente = new Incidente();
			incidente.setAveriguacionPrevia(registroModel.getAveriguacionPrevia());
			incidente.setFolio(registroModel.getIncidenteModel().getFolio());
			incidente.setIdMotivo(registroModel.getIncidenteModel().getMotivo());
			incidente.setIdOrigen(registroModel.getVehiculoInvolucradoModel().getOrigen());
			incidente.setObservaciones(registroModel.getObservaciones());
			
			eventoRepository.setIncidente(incidente);
			eventoRepository.setUsuario(registroModel.getIncidenteModel().getUsuario());
			
			//vehiculo
			VehiculoInvolucrado vehiculoInvolucrado = new VehiculoInvolucrado();
			vehiculoInvolucrado.setColor(registroModel.getVehiculoInvolucradoModel().getColor());
			vehiculoInvolucrado.setIdMarca(registroModel.getVehiculoInvolucradoModel().getIdMarca());
			vehiculoInvolucrado.setIdProcedencia(registroModel.getVehiculoInvolucradoModel().getIdProcedencia());
			vehiculoInvolucrado.setIdSubMarca(registroModel.getVehiculoInvolucradoModel().getIdSubMarca());
			vehiculoInvolucrado.setIdTipo(registroModel.getVehiculoInvolucradoModel().getIdTipo());
			vehiculoInvolucrado.setModelo(registroModel.getVehiculoInvolucradoModel().getModelo());
			vehiculoInvolucrado.setPlaca(registroModel.getVehiculoInvolucradoModel().getPlaca().replaceAll("[^A-Z0-9]", ""));
			vehiculoInvolucrado.setTipoLista(registroModel.getVehiculoInvolucradoModel().getTipoLista());
			
			eventoRepository.setVehiculoInvolucrado(vehiculoInvolucrado);
			
		}
		
		System.out.println("se manda---" + eventoRepository.toString());
		
		return eventoRepository;
		
	}
	
	
	public static EventoPeticion domainToRepository(EventoModel eventoModel) {
		
		EventoPeticion eventoPeticion = new EventoPeticion();
		
		eventoPeticion.setAveriguacionPrevia(eventoModel.getAveriguacionPrevia());
//		eventoPeticion.setColor(Integer.valueOf(eventoModel.getColor()));
		eventoPeticion.setColor(0);
		eventoPeticion.setIdMarca(eventoModel.getIdMarca());
		eventoPeticion.setIdProcedencia(eventoModel.getIdProcedencia());
		eventoPeticion.setIdSubMarca(eventoModel.getIdSubMarca());
		eventoPeticion.setIdTipo(eventoModel.getIdTipo());
		eventoPeticion.setPlacaB64(eventoModel.getPlacaB64());
		eventoPeticion.setModelo(eventoModel.getModelo());
		eventoPeticion.setObservaciones(eventoModel.getObservaciones());
		eventoPeticion.setOrigen(eventoModel.getOrigen());
		eventoPeticion.setPlaca(eventoModel.getPlaca());
		eventoPeticion.setTipoLista(eventoModel.getTipoLista());
		eventoPeticion.setIntegracionOrigen("LPR");
		
		//eventoPeticion.setSeniasParticulares(seniasParticulares);
		eventoPeticion.setClaveDispositivo(eventoModel.getClaveDispositivo());
		//eventoPeticion.setIdEventoVehiculo(eventoModel.getidEventoVehiculo);
		eventoPeticion.setUuid(5);
		
		System.out.println("Se recibe---" +eventoPeticion.toString());
		
		return eventoPeticion;
		
	}
	
}
