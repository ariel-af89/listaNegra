package com.promad.msi.listaplacas.helper;

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
			vehiculoInvolucrado.setPlaca(registroModel.getVehiculoInvolucradoModel().getPlaca());
			vehiculoInvolucrado.setTipoLista(registroModel.getVehiculoInvolucradoModel().getTipoLista());
			
			eventoRepository.setVehiculoInvolucrado(vehiculoInvolucrado);
		}
		
		return eventoRepository;
		
	}
	
}
