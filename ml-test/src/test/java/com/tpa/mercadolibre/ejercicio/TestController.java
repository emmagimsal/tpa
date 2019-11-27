package com.tpa.mercadolibre.ejercicio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.tpa.mercadolibre.ejercicio.controller.DnaController;
import com.tpa.mercadolibre.ejercicio.repository.DnaRepository;

/**
 * @author rvarela
 *
 */
@TestPropertySource(locations = "classpath:application.properties")

@SpringBootTest
@AutoConfigureMockMvc
@Execution(ExecutionMode.CONCURRENT)
public class TestController {

	@Autowired
	DnaRepository dnaRepo;
	
	@Autowired
	DnaController dnaController;

	@DisplayName("Test DNa  Mutant")
	@Test
	@RepeatedTest(2)
	public void testMutant(TestInfo testInfo) throws Exception {
		
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		
		assertTrue(dnaController.doJob(dna));
		
	}
	
	
	@DisplayName("Test DNa No Mutant")
	@RepeatedTest(2)
	public void testNoMutant(TestInfo testInfo) throws Exception {
		
		String[] dnaC = { "ATGCGA", "CAGTGC", "TAATGT", "AGACAG", "GCGTCA", "TCACTG" };
		
		assertFalse(dnaController.doJob(dnaC));
		
	}

}
