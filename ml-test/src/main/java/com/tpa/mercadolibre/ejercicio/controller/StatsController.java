package com.tpa.mercadolibre.ejercicio.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tpa.mercadolibre.ejercicio.dto.ADN;
import com.tpa.mercadolibre.ejercicio.repository.DnaRepository;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class StatsController {

	@Autowired
	DnaRepository dnaRepo;

	@GetMapping(consumes = "application/json", produces = "application/json", value = "/stats")
	@Transactional
	public ResponseEntity getStats() {

//		int count_mutant_dna;
//		int count_human_dna;
//		int ratio;

		ADN adn = new ADN();

		Optional<Object> mutantCount = dnaRepo.getMutantCount();

		Optional<Object> noMutantCount = dnaRepo.getNoMutantCount();

		Optional<Object> average = dnaRepo.getAverage();

		if (mutantCount.isPresent()) {
			adn.setCount_mutant_dna((Long) mutantCount.get());
		}

		if (noMutantCount.isPresent()) {
			adn.setCount_human_dna((Long) noMutantCount.get());
		}

		if (average.isPresent()) {
			adn.setRatio((Double) average.get());
		}

//		ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
		try {
			return ResponseEntity.status(HttpStatus.OK).body(adn);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + e.getMessage() + ". \"}");

		}
	}

}
