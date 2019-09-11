package com.tpa.jpa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tpa.jpa.entity.Usuario;
import com.tpa.jpa.repository.UsuarioRepository;

@Controller
public class ModelBasicController {

	@Autowired
	UsuarioRepository userRepo;

	@RequestMapping(path = "/usuario/basic/list")
	public String list(Model model) {
		model.addAttribute("listadoUsuarios", userRepo.findAll());

		return "model/usuario/basic/list";
	}

	@RequestMapping(path = "/usuario/basic/add", method = RequestMethod.GET)
	public String add(Model model) {

		model.addAttribute("usuario", new Usuario());

		return "model/usuario/basic/edit";
	}

	@RequestMapping(path = "/usuario/basic/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable(value = "id") String id) {
		model.addAttribute("usuario", userRepo.findById(Long.valueOf(id)));
		return "model/usuario/basic/edit";
	}

	@RequestMapping(path = "/usuario/basic/create", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid Usuario usuario, BindingResult bindingResult, Model model) {

		ModelAndView mav = new ModelAndView();

		if (bindingResult.hasErrors()) {
			mav.setViewName("model/usuario/basic/edit");
			return mav;
		}

		
		userRepo.save(usuario);
		mav.setViewName("redirect:/usuario/basic/list");
		return mav;
	}

	@RequestMapping(path = "/usuario/basic/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id") String id, Model model) {

		try {

			userRepo.deleteById((Long.valueOf(id)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/usuario/basic/list/";

	}

}