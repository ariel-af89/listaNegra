package com.promad.msi.listaplacas.utilities;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.promad.msi.listaplacas.errorhandling.PlacaRegisterRetryListener;
import com.promad.msi.listaplacas.model.IncidenteModel;
import com.promad.msi.listaplacas.model.RegistroModel;
import com.promad.msi.listaplacas.model.VehiculoInvolucradoModel;

public class JSONManager {
	private final static Log LOG = LogFactory.getLog(PlacaRegisterRetryListener.class);

	public static File getJsonFile() {
		String startDir = System.getProperty("user.dir");
		LOG.info("PATH -->" + startDir);
		File archivo = new File(startDir + "\\registros.json");
		LOG.info("obtiene archivo " + archivo);
		return archivo;
	}


	public static boolean updateJsonFile(String jsonString) {
		try {
			LOG.info("---UPDATE---");
			File archivo = getJsonFile();

			if (!archivo.isFile()) {
				try {
					archivo.createNewFile();
				} catch (IOException e) {
					LOG.error("Error al crear el archivo: " + e.getMessage());
				}
			}

			try {
				FileWriter file = new FileWriter(archivo,true);
				file.write(jsonString + ";");
				file.flush();
				file.close();
				return true;
			} catch (IOException e) {
				LOG.error("Error: " + e.getMessage());
			}
		} catch (Exception e) {
			LOG.error("Error: " + e.getMessage());
			return false;
		}
		return false;

	}

	@SuppressWarnings("unchecked")
	public static boolean saveToJsonFile(RegistroModel rm) {

		//JSONObject registro = new JSONObject();
		//JSONArray registroList = getArrayFromJsonFile();

		String registroList = null;

		try {

			Gson gson = new Gson();
			registroList = gson.toJson(rm);
/*
			JSONObject incidenteObjectJSON = new JSONObject();

			String folio = rm.getIncidenteModel().getFolio();
			incidenteObjectJSON.put("folio", folio != null ? folio : "");
			Integer motivo = rm.getIncidenteModel().getMotivo();
			incidenteObjectJSON.put("motivo", motivo != null ? motivo : 0);
			String usuario = rm.getIncidenteModel().getUsuario();
			incidenteObjectJSON.put("usuario", usuario != null ? usuario : "");
			registro.put("Incidente", incidenteObjectJSON);

			JSONObject vehiculoObjectJSON = new JSONObject();
			String placa = rm.getVehiculoInvolucradoModel().getPlaca();
			vehiculoObjectJSON.put("placa", placa != null ? placa : "");

			Integer idMarca = rm.getVehiculoInvolucradoModel().getIdMarca();
			vehiculoObjectJSON.put("idMarca", idMarca != null ? idMarca : 0);
			Integer idSubMarca = rm.getVehiculoInvolucradoModel().getIdSubMarca();
			vehiculoObjectJSON.put("idSubMarca", idSubMarca != null ? idSubMarca : 0);
			Integer modelo = rm.getVehiculoInvolucradoModel().getModelo();
			vehiculoObjectJSON.put("modelo", modelo != null ? modelo : 0);
			Integer idTipo = rm.getVehiculoInvolucradoModel().getIdTipo();
			vehiculoObjectJSON.put("idTipo", idTipo != null ? idTipo : 0);
			String color = rm.getVehiculoInvolucradoModel().getColor();
			vehiculoObjectJSON.put("color", color != null ? color : "");
			Integer idProcedencia = rm.getVehiculoInvolucradoModel().getIdProcedencia();
			vehiculoObjectJSON.put("idProcedencia", idProcedencia != null ? idProcedencia : 0);
			Integer origen = rm.getVehiculoInvolucradoModel().getOrigen();
			vehiculoObjectJSON.put("origen", origen != null ? origen : 0);
			Integer tipoLista = rm.getVehiculoInvolucradoModel().getTipoLista();
			vehiculoObjectJSON.put("tipoLista", tipoLista != null ? tipoLista : 0);
			registro.put("VehiculoInvolucrado", vehiculoObjectJSON);

			String averiguacionPrevia = rm.getAveriguacionPrevia();
			registro.put("AveriguacionPrevia", averiguacionPrevia != null ? averiguacionPrevia : "");
			String obervaciones = rm.getObservaciones();
			registro.put("Observaciones", obervaciones != null ? obervaciones : "");

			JSONObject registroObject = new JSONObject();
			registroObject.put("registro", registro);

			registroList.add(registroObject);*/

		} catch (Exception e) {
			LOG.error("Error in SAVE" + e.getMessage());
		}

		return updateJsonFile(registroList);
	}

	@SuppressWarnings("resource")
	public static List<String> getArrayFromJsonFile() {

		File archivo = getJsonFile();

		if (!archivo.isFile()) {
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				LOG.error("Error al crear el archivo: " + e.getMessage());
			}
		}

		//JSONParser jsonParser = new JSONParser();
		//RegistroModel rm = new RegistroModel();

		try  {
			FileReader reader = new FileReader(archivo);
			if(reader.read()==-1){
				LOG.info("ENTRA read == -1");
				return new ArrayList<>();
				// return new JSONArray();
			}
			else {
				LOG.info("ENTRA convierte registro");

				String startDir = System.getProperty("user.dir");
				String jsonN = new String(Files.readAllBytes(Paths.get(startDir + "\\registros.json")));

				List<String> registrosList = new ArrayList<>(Arrays.asList(jsonN.split(";")));
				System.out.println(registrosList);

/*
				String match = input.subSequence(index, m.start()).toString();
				matchList.add(match);


				JSONArray arr = new JSONArray();
				ArrayList<String> list = new ArrayList<String>();

				for(int i = 0; i < list.size(); i++){
					list.add(arr.getJSONObject(i).getString("name"));
				}


				ArrayList<String> listdata = new ArrayList<String>();
				JSONArray jArray = (JSONArray)jsonObject;
				if (jArray != null) {
					for (int i=0;i<jArray.length();i++){
						listdata.add(jArray.getString(i));
					}
				}

				//JSONArray registrosList = (JSONArray) jsonParser.parse(reader);

				/*JSONParser parser = new JSONParser();
				Object obj = parser.parse(new FileReader(startDir + "\\registros.json"));
				JSONArray registrosList = (JSONArray) obj;
				for(int i=0; i< archivo.length() ; i++) {

					registrosList.add(registrosList);
				}

				for (int i = 0; i < json.size(); i++) {

					JSONObject object =(JSONObject) json.get(i);
					String clave = object.get("points").toString();
					String title = object.get("title").toString();
					System.err.println("points:"+clave);
					System.err.println("title:"+title);

								Stream.of(str.split(","))
								.map (elem -> new String(elem))
								.collect(Collectors.toList());
					}


				friends.forEach((final String name) -> System.out.println(name));
				1
				friends.forEach((final String name) -> System.out.println(name));

				gson.toJson(myStrings); // Will cause a runtime exception

				gson.fromJson(json, myStrings.getClass());

			//Object obj = jsonParser.parse(reader);
			//JSONArray registrosList = (JSONArray) obj;

			//Codigo de prueba guardado valores JSON
				/*Writer writer = new FileWriter(archivo);
				Gson registrosList = new GsonBuilder().create(); registrosList.toJson(rm, writer); */


			if (registrosList != null && registrosList.size() > 0)
				return registrosList;
				// return new JSONArray();
			}
		} catch (FileNotFoundException e) {
			LOG.error("FileNotFoundException"+e.getMessage());
		} catch (IOException e) {
			LOG.error("IOException"+e.getMessage());
		/*}catch (ParseException e) {
			LOG.error("ParseException"+e.getMessage());*/
		}catch (Exception e) {
			LOG.error("Error get" + e.getMessage());
		}
		//return new LisJSONArray();
		return new ArrayList<>();
	}

	public static RegistroModel parseregistroObject(JSONObject indexObject) {

		RegistroModel rm = new RegistroModel();
		// Get registro object within list
		JSONObject registroJSON = (JSONObject) indexObject.get("registro");

		JSONObject incidenteJSON = (JSONObject) registroJSON.get("Incidente");
		IncidenteModel incidenteModel = new IncidenteModel();
		incidenteModel.setFolio((String) incidenteJSON.get("folio"));
		incidenteModel.setMotivo(((Long) incidenteJSON.get("motivo")).intValue());
		incidenteModel.setUsuario((String) incidenteJSON.get("usuario"));
		rm.setIncidenteModel(incidenteModel);

		JSONObject vehiculoInvolucradoJSON = (JSONObject) registroJSON.get("VehiculoInvolucrado");
		VehiculoInvolucradoModel vehiculoInvolucradoModel = new VehiculoInvolucradoModel();
		vehiculoInvolucradoModel.setPlaca((String) vehiculoInvolucradoJSON.get("placa"));

		vehiculoInvolucradoModel.setIdMarca(((Long) vehiculoInvolucradoJSON.get("idMarca")).intValue());
		vehiculoInvolucradoModel.setIdSubMarca(((Long) vehiculoInvolucradoJSON.get("idSubMarca")).intValue());
		vehiculoInvolucradoModel.setModelo(((Long) vehiculoInvolucradoJSON.get("modelo")).intValue());
		vehiculoInvolucradoModel.setIdTipo(((Long) vehiculoInvolucradoJSON.get("idTipo")).intValue());
		vehiculoInvolucradoModel.setColor((String) vehiculoInvolucradoJSON.get("color"));
		vehiculoInvolucradoModel.setIdProcedencia(((Long) vehiculoInvolucradoJSON.get("idProcedencia")).intValue());
		vehiculoInvolucradoModel.setOrigen(((Long) vehiculoInvolucradoJSON.get("origen")).intValue());
		vehiculoInvolucradoModel.setTipoLista(((Long) vehiculoInvolucradoJSON.get("tipoLista")).intValue());

		rm.setVehiculoInvolucradoModel(vehiculoInvolucradoModel);

		String averiguacionPrevia = (String) registroJSON.get("averiguacionPrevia");
		rm.setAveriguacionPrevia(averiguacionPrevia);
		String observaciones = (String) registroJSON.get("observaciones");
		rm.setObservaciones(observaciones);

		return rm;

	}

}
