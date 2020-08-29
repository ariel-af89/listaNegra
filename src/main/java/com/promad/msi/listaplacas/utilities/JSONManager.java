package com.promad.msi.listaplacas.utilities;


import java.io.FileWriter;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.promad.msi.listaplacas.model.IncidenteModel;
import com.promad.msi.listaplacas.model.RegistroModel;
import com.promad.msi.listaplacas.model.VehiculoInvolucradoModel;

public class JSONManager {
	
	public static boolean updateJsonFile( String jsonString ) {

	       //Write JSON file
	        try (FileWriter file = new FileWriter("registros.json")) {
	 
	            file.write(jsonString);
	            file.flush();
	            
	            return true;
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return false;
		
	}
	
    public static boolean saveToJsonFile( RegistroModel rm )
    {
        //First registro
        JSONObject registro = new JSONObject();
        
        JSONObject incidenteObjectJSON = new JSONObject();
        
        String folio=rm.getIncidenteModel().getFolio() ; 
        incidenteObjectJSON.put("folio", folio!=null ? folio : "");
        Integer motivo = rm.getIncidenteModel().getMotivo();
        incidenteObjectJSON.put("motivo", motivo!=null ? motivo :0);
        String usuario=rm.getIncidenteModel().getUsuario();
        incidenteObjectJSON.put("usuario", usuario!=null ? usuario : "");
        registro.put("Incidente", incidenteObjectJSON);
        
        JSONObject vehiculoObjectJSON = new JSONObject();
        String placa =rm.getVehiculoInvolucradoModel().getPlaca() ;
        vehiculoObjectJSON.put("placa",placa!=null ? placa : "");
        
        Integer idMarca = rm.getVehiculoInvolucradoModel().getIdMarca();
        vehiculoObjectJSON.put("idMarca", idMarca!=null ? idMarca : 0);
        Integer idSubMarca = rm.getVehiculoInvolucradoModel().getIdSubMarca();
        vehiculoObjectJSON.put("idSubMarca",   idSubMarca!=null ? idSubMarca : 0);
        Integer modelo = rm.getVehiculoInvolucradoModel().getModelo() ;
        vehiculoObjectJSON.put("modelo",modelo!=null ? modelo : 0);
        Integer idTipo = rm.getVehiculoInvolucradoModel().getIdTipo();
        vehiculoObjectJSON.put("idTipo", idTipo!=null ? idTipo : 0 );
        String color = rm.getVehiculoInvolucradoModel().getColor();
        vehiculoObjectJSON.put("color", color != null ? color : "" );
        Integer idProcedencia= rm.getVehiculoInvolucradoModel().getIdProcedencia();
        vehiculoObjectJSON.put("idProcedencia", idProcedencia!=null ? idProcedencia : 0);
        Integer origen = rm.getVehiculoInvolucradoModel().getOrigen();
        vehiculoObjectJSON.put("origen", origen!=null?  origen : 0);
        Integer tipoLista= rm.getVehiculoInvolucradoModel().getTipoLista();
        vehiculoObjectJSON.put("tipoLista",tipoLista!=null ? tipoLista : 0);
        registro.put("VehiculoInvolucrado",vehiculoObjectJSON);
        
        String averiguacionPrevia= rm.getAveriguacionPrevia();
        registro.put("AveriguacionPrevia",  averiguacionPrevia!=null ? averiguacionPrevia:"");
        String obervaciones= rm.getObservaciones();
        registro.put("Observaciones", obervaciones!=null? obervaciones : "" );
         
        JSONObject registroObject = new JSONObject(); 
        registroObject.put("registro", registro);
              
        //Add registro to list
        JSONArray registroList = getArrayFromJsonFile();
        registroList.add(registroObject);
        
        return updateJsonFile(registroList.toJSONString());
    }
    
    
    
    public static JSONArray  getArrayFromJsonFile() 
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        RegistroModel rm= new RegistroModel(); 
        
        try (FileReader reader = new FileReader("registros.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader); 
            JSONArray registrosList = (JSONArray) obj;
            if(registrosList!=null && registrosList.size()>0)
            return registrosList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return new JSONArray();
    }
 
    public static RegistroModel  parseregistroObject(JSONObject indexObject) 
    {
    	
    	RegistroModel rm= new RegistroModel();
        //Get registro object within list
        JSONObject registroJSON = (JSONObject) indexObject.get("registro");  
        
        
        JSONObject incidenteJSON = (JSONObject) registroJSON.get("Incidente");
        IncidenteModel incidenteModel = new IncidenteModel();
        incidenteModel.setFolio((String)incidenteJSON.get("folio"));
        incidenteModel.setMotivo(((Long)incidenteJSON.get("motivo")).intValue());
        incidenteModel.setUsuario((String)incidenteJSON.get("usuario"));        
        rm.setIncidenteModel(incidenteModel);

        JSONObject vehiculoInvolucradoJSON = (JSONObject) registroJSON.get("VehiculoInvolucrado");
        VehiculoInvolucradoModel vehiculoInvolucradoModel = new VehiculoInvolucradoModel() ;
        vehiculoInvolucradoModel.setPlaca((String)vehiculoInvolucradoJSON.get("placa"));
        
        vehiculoInvolucradoModel.setIdMarca(( (Long)vehiculoInvolucradoJSON.get("idMarca")).intValue());
        vehiculoInvolucradoModel.setIdSubMarca(((Long)vehiculoInvolucradoJSON.get("idSubMarca")).intValue());
        vehiculoInvolucradoModel.setModelo(((Long)vehiculoInvolucradoJSON.get("modelo")).intValue());
        vehiculoInvolucradoModel.setIdTipo(((Long)vehiculoInvolucradoJSON.get("idTipo")).intValue());
        vehiculoInvolucradoModel.setColor((String)vehiculoInvolucradoJSON.get("color"));
        vehiculoInvolucradoModel.setIdProcedencia(((Long)vehiculoInvolucradoJSON.get("idProcedencia")).intValue());
        vehiculoInvolucradoModel.setOrigen(((Long)vehiculoInvolucradoJSON.get("origen")).intValue());
        vehiculoInvolucradoModel.setTipoLista(((Long)vehiculoInvolucradoJSON.get("tipoLista")).intValue());
        
        rm.setVehiculoInvolucradoModel(vehiculoInvolucradoModel);

         String averiguacionPrevia = (String) registroJSON.get("averiguacionPrevia");   
         rm.setAveriguacionPrevia(averiguacionPrevia);
         String observaciones = (String) registroJSON.get("observaciones");  
         rm.setObservaciones(observaciones);
         
         return rm;

    }
    
    
    
}