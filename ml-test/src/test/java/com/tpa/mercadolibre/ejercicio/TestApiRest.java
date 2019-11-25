package com.tpa.mercadolibre.ejercicio;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tpa.mercadolibre.ejercicio.repository.DnaRepository;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")

@SpringBootTest
@AutoConfigureMockMvc

public class TestApiRest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	DnaRepository dnaRepo;

	
//	@DisplayName("Test Api Mutant DNA ")
//	@Test
//	public void TestIsMutant3(TestInfo testInfo) throws Exception {
//
//
//		String[] dnaC = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
//		
//		
//		
//		MvcResult result = mvc.perform(post("/mutant/")
//				.param("dna", asJsonString(dnaC))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content()
//						.contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
//				.andExpect(content()
//						.string("OK"))
//				.andDo(MockMvcResultHandlers.print())
//				.andExpect(status()
//						.isOk())
//				.andReturn();
//
//		String content = result.getResponse().getContentAsString();
//
//		System.out.println(" Param send "+asJsonString(dnaC)+ "  Api Return ===> " + content);
//
//	}
	
//	@DisplayName("Test Api NO Mutant DNA ")
//	@Test
//	public void TestIsNotMutant(TestInfo testInfo) throws Exception {
//
//
//		String[] dnaC = { "ATGCGA", "CAGTGC", "TAATGT", "AGACAG", "GCGTCA", "TCACTG" };
//		
//		MvcResult result = mvc.perform(post("/mutant/")
//				.param("dna", asJsonString(dnaC))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isForbidden())
//				.andExpect(content()
//						.contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
//				.andExpect(content()
//						.string(""))
//				.andDo(MockMvcResultHandlers.print())
//				.andExpect(status()
//						.isForbidden())
//				.andReturn();
//
//		String content = result.getResponse().getContentAsString();
//
//		System.out.println(" Param send "+asJsonString(dnaC)+ "  Api Return ===> " + content);
//	}
	
	
	@DisplayName("Test Stats ")
	@Test
	public void testStats(TestInfo testInfo) throws Exception {

//		{“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
		
		MvcResult result = mvc.perform(get("/stats")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.count_mutant_dna", is(notNullValue())))
				.andExpect(jsonPath("$.count_human_dna", is(notNullValue())))
				.andExpect(jsonPath("$.ratio", is(notNullValue())))
				.andDo(MockMvcResultHandlers.print())
				
				.andReturn();

		String content = result.getResponse().getContentAsString();
		
		System.out.println("  Api Return ===> " + content);

	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}


}
