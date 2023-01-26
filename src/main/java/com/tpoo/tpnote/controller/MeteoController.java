package com.tpoo.tpnote.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;

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
		String token;
		BufferedReader rd;
		try {
			rd = new BufferedReader(new FileReader(new File("token.txt")));
			token = rd.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			token = "";
		}
		
		SimpleClientHttpRequestFactory clientHttpsReq = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("proxy.univ-lemans.fr", 3128));
		clientHttpsReq.setProxy(proxy);
		RestTemplate restTemplate = new RestTemplate(clientHttpsReq);
    	String addressCall = address.replace(" ", "+");
		JsonRoot root = restTemplate.getForObject("https://api-adresse.data.gouv.fr/search/?q="+addressCall, JsonRoot.class);
		ArrayList<Double> coordinates = root.getFeatures().get(0).getGeometry().getCoordinates();
    	model.addAttribute("AddresseTemplate", ""+coordinates.get(1)+","+coordinates.get(0));
    	
    	MeteoJsonRoot meteoResponse = restTemplate.getForObject("https://api.meteo-concept.com/api/forecast/daily/0?token="+token+"&latlng="+coordinates.get(1)+","+coordinates.get(0), MeteoJsonRoot.class);
    	//coordinate (1,0) pour les latlng
    	//https://api.meteo-concept.com/api/forecast/daily/0?token=TOKEN ICI BATAR &latlng=X,X
		return "meteo";
	}
}
