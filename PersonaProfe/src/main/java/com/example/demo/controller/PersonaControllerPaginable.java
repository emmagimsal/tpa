package com.example.demo.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Persona;
import com.example.demo.repository.paging.PersonaRepositoryPaging;

//@Controller
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@RequestMapping(path = "api/v2/persona/findBy")
public class PersonaControllerPaginable {

	@Autowired
	PersonaRepositoryPaging personaRepo;

	@GetMapping("/")

	@Transactional
	public Page<Persona> getAll(Pageable pageable) {

		try {

			return personaRepo.findAllOrderByNombre(pageable);

		} catch (Exception e) {

		}
		return null;

	}

	@GetMapping("/dni/{dni}")
	@Transactional
	public ResponseEntity getFindByDni(Pageable pageable, @PathVariable Integer dni) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(personaRepo.findByDni(pageable, dni));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error. \"}");

		}

	}

	@GetMapping("/apellido/{apellido}")
	@Transactional
	public ResponseEntity getFindByApellido(Pageable pageable, @PathVariable String apellido) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(personaRepo.findByApellido(pageable, apellido));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error. \"}");
		}

	}
	
	
	@GetMapping("/nombre/{nombre}")
	@Transactional
	public ResponseEntity getFindByNombre(Pageable pageable, @PathVariable String nombre) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(personaRepo.findByNombre(pageable, nombre));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error. \"}");
		}

	}
}
