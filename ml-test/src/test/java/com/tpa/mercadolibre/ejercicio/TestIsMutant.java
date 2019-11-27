package com.tpa.mercadolibre.ejercicio;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.tpa.mercadolibre.ejercicio.adn.MutantAdnScanner;
import com.tpa.mercadolibre.ejercicio.exception.ArrayNullException;
import com.tpa.mercadolibre.ejercicio.exception.MatrixNullException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TestIsMutant {

	public static String TEST_SEPARATOR = "\t\t >";
	public static String CLASS_SEPARATOR = "\t@";

	@BeforeEach
	void init(TestInfo testInfo) {
		String callingTest = testInfo.getTestMethod().get().getName();
		System.out.println(TEST_SEPARATOR + "Before [" + callingTest + " ]");
	}

	@AfterEach
	void after(TestInfo testInfo) {
		String callingTest = testInfo.getTestMethod().get().getName();
		System.out.println(TEST_SEPARATOR + "After [" + callingTest + " ]");
	}

	@BeforeAll
	static void init() {
		System.out.println(CLASS_SEPARATOR + " @BeforeAll");
	}

	@AfterAll
	static void after() {
		System.out.println(CLASS_SEPARATOR + " @AfterAll");
	}

	@DisplayName("TestIsMutant")
	@Test
	public void TestIsMutant(TestInfo testInfo) throws ArrayNullException {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		boolean debug = false;

		System.out.println(
				"\t\t\tRunning [ ADN " + ((MutantAdnScanner.isMutant(dna, debug)) ? "Mutante!!" : "No Mutante") + "]");
	}

	@DisplayName("TestGetRows")
	@Test
	public void TestGetRows(TestInfo testInfo) {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		char[][] matrix = MutantAdnScanner.convert(dna);

		MutantAdnScanner.getRows(matrix);

		for (String string : MutantAdnScanner.getRows(matrix)) {
			System.out.println(string);
		}

	}

	@DisplayName("testGetColumns")
	@Test
	public void testGetColumns(TestInfo testInfo) {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		char[][] matrix = MutantAdnScanner.convert(dna);

		MutantAdnScanner.getRows(matrix);

		for (String string : MutantAdnScanner.getColumns(matrix)) {
			System.out.println(string);
		}

	}

	@DisplayName("testGetDiagonal")
	@Test
	public void testGetDiagonal(TestInfo testInfo) {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		char[][] matrix = MutantAdnScanner.convert(dna);

		MutantAdnScanner.getRows(matrix);

		for (String string : MutantAdnScanner.getDiagonal(matrix, true)) {
			System.out.println(string);
		}

	}

	@DisplayName("testGetDiagonal2")
	@Test
	public void testGetDiagonal2(TestInfo testInfo) {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		char[][] matrix = MutantAdnScanner.convert(dna);

		MutantAdnScanner.getRows(matrix);

		for (String string : MutantAdnScanner.getDiagonal(matrix, false)) {
			System.out.println(string);
		}

	}

	@DisplayName("testValidation - ArrayNullException")
	@Test
	public void testValidation(TestInfo testInfo) throws ArrayNullException {
		String[] dna = null;
		
		assertThrows(MatrixNullException.class, () -> MutantAdnScanner.isMutant(dna, true));
	}

	@DisplayName("testValidation2 - ArrayNullException")
	@Test
	public void testValidation2(TestInfo testInfo) throws ArrayNullException {
		String[] dna = null;
		
		assertThrows(ArrayNullException.class, () -> MutantAdnScanner.isMutant(dna, true));
	}

}
