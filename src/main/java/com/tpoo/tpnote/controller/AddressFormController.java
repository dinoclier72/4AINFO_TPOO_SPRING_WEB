package com.tpoo.tpnote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressFormController {
	@GetMapping("/adresse")
	public String showAddresses(Model model) {
        return "addresseForm";
	}
}
