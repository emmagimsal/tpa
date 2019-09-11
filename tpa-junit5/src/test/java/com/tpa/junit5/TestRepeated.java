package com.tpa.junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodLengthOrderer.class)
public class TestRepeated {

	public static String TEST_SEPARATOR = "\t\t >";
	public static String CLASS_SEPARATOR = "\t@";

	@BeforeEach
	void init(TestInfo testInfo) {
		String callingTest = testInfo.getTestMethod().get().getName();
		System.out.println(TEST_SEPARATOR + "Before ["+callingTest+" ]");
	}

	@AfterEach
	void after(TestInfo testInfo) {
		String callingTest = testInfo.getTestMethod().get().getName();
		System.out.println(TEST_SEPARATOR + "After ["+callingTest+" ]");
	}

	@BeforeAll
	static void begin() {
		System.out.println(CLASS_SEPARATOR + " @BeforeAll");
	}

	@AfterAll
	static void end() {
		System.out.println(CLASS_SEPARATOR + " @AfterAll");
	}

	 @DisplayName("test_A1")
	 @RepeatedTest(value = 5, name = "{displayName} - iteracion {currentRepetition} of {totalRepetitions}")
	public void test_A1(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		System.out.println("\t\t\tRunning [ iteración Nº " +repetitionInfo.getCurrentRepetition() + "]");

	}
	

}
