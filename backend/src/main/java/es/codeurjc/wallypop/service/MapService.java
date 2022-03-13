package es.codeurjc.wallypop.service;

import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MapService {
	public static String[] getLatitudeLongitude(String direction) throws Exception {
		URL url = new URL(
				"https://nominatim.openstreetmap.org/?addressdetails=1&q=" + direction + "&format=json&limit=1");
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonResult = mapper.readTree(url);
			String[] lat_lon = { jsonResult.get(0).get("lat").asText(), jsonResult.get(0).get("lon").asText() };
			return lat_lon;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
