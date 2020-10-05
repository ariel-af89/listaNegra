package com.promad.msi.listaplacas.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	private final static JSONArray registrosList = new JSONArray();

	@SuppressWarnings("unchecked")
	public static boolean saveToJsonArray(RegistroModel rm) {

		String registro = null;
		try {
			Gson gson = new Gson();
			registro = gson.toJson(rm);
			System.out.println("REGISTRO A GUARDAR" + registro);
			} catch (Exception e) {
			LOG.error("Error in SAVE" + e.getMessage());
		}
		return updateJsonArray(registro);
	}

	public static boolean updateJsonArray(String registro) {
			try {
				registrosList.add(registro);
				System.out.println("LISTA ACTUALIZADA" + registrosList);
				return true;
			} catch (Exception e) {
				LOG.error("Error in SAVE registro" + e.getMessage());
			}
		return false;
	}


	@SuppressWarnings("resource")
	public static JSONArray getArrayJSON() {

		try  {
			if (registrosList != null && registrosList.size() > 0)
				return registrosList;

		}catch (Exception e) {
			LOG.error("Error get" + e.getMessage());
		}
		return new JSONArray();
	}
}
