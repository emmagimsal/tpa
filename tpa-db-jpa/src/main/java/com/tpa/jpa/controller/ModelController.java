package com.tpa.jpa.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tpa.jpa.entity.Usuario;
import com.tpa.jpa.repository.UsuarioRepository;
import com.tpa.jpa.repository.UsuarioRepositoryPaging;

@Controller
public class ModelController {


	@Autowired
	UsuarioRepository userRepo;

	@RequestMapping(path = "/usuario/list")
	public String list(Model model) {
		model.addAttribute("listadoUsuarios", userRepo.findAll());
		 
		return "model/usuario/list";
	}
	

	
	@RequestMapping(path = "/usuario/add", method = RequestMethod.GET)
	public String add(Model model) {

		model.addAttribute("usuario", new Usuario());

		return "model/usuario/edit";
	}

	@RequestMapping(path = "/usuario/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable(value = "id") String id) {
		
		model.addAttribute("usuario", 	userRepo.findById(Long.valueOf(id)));
		
		return "model/usuario/edit";
	}

	@RequestMapping(path = "/usuario/create", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid Usuario usuario,
			BindingResult bindingResult, Model model) {

		ModelAndView mav = new ModelAndView();

		if (bindingResult.hasErrors()) {
			mav.setViewName("model/usuario/edit");
			return mav;
		}

		try {
			userRepo.save(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			bindingResult.rejectValue("nombre", "error.nombre", "nombre duplicado");
			
			e.printStackTrace();
			mav.setViewName("model/usuario/edit");
			return mav;
		}
		
		
		mav.setViewName("redirect:/usuario/list");
		return mav;
	}

	@RequestMapping(path = "/usuario/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id") String id , Model model) {
		
		try {
			userRepo.deleteById((Long.valueOf(id)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/usuario/list/";
	}
	
	
	@RequestMapping(path = "/usuario/buscarPorNombre/{valorBusqueda}", method = RequestMethod.GET)
	public String buscarPorNombre(Model model,  @PathVariable(name = "valorBusqueda") String nombre) {
		
		
		if(nombre!=null && nombre.equalsIgnoreCase("-"))
			model.addAttribute("listadoUsuarios", userRepo.findAll());
			else
			model.addAttribute("listadoUsuarios", userRepo.findByNombre(nombre));
		 
		return "model/usuario/list:: usuariosListFragment";
		
	}

}