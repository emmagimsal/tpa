package com.tpa.mercadolibre.ejercicio.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpa.mercadolibre.ejercicio.adn.MutantAdnScanner;
import com.tpa.mercadolibre.ejercicio.entity.Dna;
import com.tpa.mercadolibre.ejercicio.repository.DnaRepository;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@RequestMapping(path = "mutant")
public class DnaController {

	@Autowired
	DnaRepository dnaRepo;
	
	private boolean debug=false;

	@PostMapping(consumes = "application/json",value = "/")
	@Transactional
	public ResponseEntity isMutantDNA(@RequestParam(value = "dna") String dna) {

		ObjectMapper objectMapper = new ObjectMapper();
		String[] dna_ =null;
		
		try {
			dna_ = objectMapper.readValue(dna, String[].class);
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		
	
		
		try {
			if (doJob(dna_)) {

				return ResponseEntity.status(HttpStatus.OK).body("OK");
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
			}

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \""+e.getMessage()+". \"}");

		}
	}

	
//	@PostMapping("/")
//	@Transactional
//	public ResponseEntity getFindByDni(Pageable pageable, @Valid @RequestBody String[] dna) {
//
//		try {
//			if (doJob(dna)) {
//
//				return ResponseEntity.status(HttpStatus.OK).body(dna);
//			} else {
//				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Error. \"}");
//			}
//
//		} catch (Exception e) {
//
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error. \"}");
//
//		}
//	}

	public boolean doJob(String[] dna) throws Exception {
		
		boolean rta=false;
		Dna dnaEntity = new Dna();
		List<String> container = Arrays.asList(dna);
		dnaEntity.setDnaCode(container);
		dnaEntity.setHascode(dnaEntity.hashCode());

		Optional<Dna> entity = dnaRepo.findByHashCode(dnaEntity.getHascode());
	

		try {
			if (MutantAdnScanner.isMutant(dna, debug)) {
				dnaEntity.setMutant(1);
				rta= true;
			} else {
				rta= false;
				dnaEntity.setMutant(0);
			}
			
			
			if (!entity.isPresent()) {
				dnaRepo.save(dnaEntity);
			}
			
			return rta;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
	}

}
