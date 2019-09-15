package com.tpa.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping(path = "/")
	public String index(Model model) {

		return "common/index";
	}
}
