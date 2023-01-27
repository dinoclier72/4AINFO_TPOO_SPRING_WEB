package com.tpoo.tpnote.controller;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.tpoo.tpnote.JsonRoot;
import com.tpoo.tpnote.MeteoJsonRoot;

import jakarta.persistence.criteria.Root;

@Controller
public class MeteoController {
	@PostMapping("/meteo")
	public String showMeteo(Model model, String address){
		model.addAttribute("AddresseTemplate", address);
		String token;
		BufferedReader rd;
		List<String> CorespondanceMeteo = new ArrayList<String>();
		try {
			rd = new BufferedReader(new InputStreamReader(new FileInputStream("code_temps.csv"), StandardCharsets.UTF_8));
			String ligne = rd.readLine();
			while((ligne = rd.readLine()) != null){
				String[] data = ligne.split(",");
				CorespondanceMeteo.add(data[1]);
			}
			rd = new BufferedReader(new FileReader(new File("token.txt")));
			token = rd.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			token = "";
		}
		RestTemplate restTemplate = new RestTemplate();
    	String addressCall = address.replace(" ", "+");
		JsonRoot root = restTemplate.getForObject("https://api-adresse.data.gouv.fr/search/?q="+addressCall, JsonRoot.class);
		ArrayList<Double> coordinates = root.getFeatures().get(0).getGeometry().getCoordinates();
		model.addAttribute("CoordonnesTemplate", ""+coordinates.get(1)+","+coordinates.get(0));
    	
    	MeteoJsonRoot meteoResponse = restTemplate.getForObject("https://api.meteo-concept.com/api/forecast/daily/0?token="+token+"&latlng="+coordinates.get(1)+","+coordinates.get(0), MeteoJsonRoot.class);

		model.addAttribute("TemperatureTemplate", "de "+meteoResponse.getForecast().getTmin()+"°C à "+meteoResponse.getForecast().getTmax()+"°C");
		model.addAttribute("MeteoTemplate", CorespondanceMeteo.get(meteoResponse.getForecast().getWeather()));
		return "meteo";
	}
}
