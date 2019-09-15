package com.tpa.jpa.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tpa.jpa.entity.Usuario;
import com.tpa.jpa.repository.UsuarioRepositoryPaging;

@Controller
public class ModelControllerV2 {

	@Autowired
	UsuarioRepositoryPaging userRepo;

	@RequestMapping(path = "/usuario/listV2")
	public String list(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("ajax") Optional<Integer> ajax) {

		int pageNumber_ = page.orElse(1);
		int pageSize_ = size.orElse(10);

		Page<Usuario> paginaUsuarios = userRepo.findAllUsuarios(PageRequest.of(pageNumber_, pageSize_));

		model.addAttribute("pagNum", pageNumber_);
		model.addAttribute("listadoUsuarios", paginaUsuarios);

		int totalPages = paginaUsuarios.getTotalPages()-1;
		if (totalPages >= 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "model/usuario/listV2";
	}
	
	

}
