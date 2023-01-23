package com.tpoo.tpnote.controller;

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

import jakarta.persistence.criteria.Root;

@Controller
public class MeteoController {
	@PostMapping("/meteo")
	public String showMeteo(Model model, String address) {
		String token = "tu l'aura pas";
		
		SimpleClientHttpRequestFactory clientHttpsReq = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("proxy.univ-lemans.fr", 3128));
		clientHttpsReq.setProxy(proxy);
		RestTemplate restTemplate = new RestTemplate(clientHttpsReq);
    	String addressCall = address.replace(" ", "+");
		JsonRoot root = restTemplate.getForObject("https://api-adresse.data.gouv.fr/search/?q="+addressCall, JsonRoot.class);
		ArrayList<Double> coordinates = root.getFeatures().get(0).getGeometry().getCoordinates();
    	model.addAttribute("AddresseTemplate", ""+coordinates.get(0)+";"+coordinates.get(1));
    	//coordinate (1,0) pour les latlng
    	//https://api.meteo-concept.com/api/forecast/daily/0?token=TOKEN ICI BATAR &latlng=X,X
		return "meteo";
	}
}
