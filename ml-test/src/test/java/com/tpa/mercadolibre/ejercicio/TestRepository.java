package com.tpa.mercadolibre.ejercicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.tpa.mercadolibre.ejercicio.controller.DnaController;
import com.tpa.mercadolibre.ejercicio.entity.Dna;
import com.tpa.mercadolibre.ejercicio.repository.DnaRepository;

/**
 * @author rvarela
 *
 */
@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class TestRepository {

	@Autowired
	DnaRepository dnaRepo;

	@Autowired
	DnaController dnaController;

	@Order(1)
	@Test
	void injectedComponentsAreNotNull() {
		assertThat(dnaController).isNotNull();
		assertThat(dnaRepo).isNotNull();

		System.out.println(1);
	}

	@Order(3)
	@DisplayName("Test Save on Repo")
	@Test
	@Disabled
	@RepeatedTest(3)
	public void saveNewDna(TestInfo testInfo) throws Exception {
		String[] dna = { "ATGCGA", "CAGTGC", "TAATGT", "AGACAG", "GCGTCA", "TCACTG" };

//		Dna dnaEntity = new Dna();
//		List<String> container = Arrays.asList(dna);
//		dnaEntity.setDnaCode(container);
//		dnaEntity.setHascode(dnaEntity.hashCode());
//		
//		dnaRepo.save(dnaEntity);
//		
//		assertThat(dnaEntity.getId()).isNotNull();
		System.out.println(3);
	}

	@Order(2)
	@DisplayName("Test Find on Repo")
	@Test
	@RepeatedTest(value = 5, name = "{displayName} ---> {currentRepetition}")
	public void testFindDna(TestInfo testInfo) throws Exception {
		String[] dna = { "ATGCGA", "CAGTGC", "TAATGT", "AGACAG", "GCGTCA", "TCACTG" };

		Dna dnaEntity = new Dna();
		List<String> container = Arrays.asList(dna);
		dnaEntity.setDnaCode(container);
		dnaEntity.setHascode(dnaEntity.hashCode());

		Optional<Dna> entity = dnaRepo.findByHashCode(dnaEntity.getHascode());

		assertTrue(entity.isPresent());

		assertThat(entity.get()).isNotNull();
		System.out.println(2);

	}

	@ParameterizedTest
	@ValueSource(ints = { 6, 8, 2, 9, 11 })
	void TestMenorQue(int number) {
		assertTrue("the number isn't less than 10", number < 10);

	}

}
