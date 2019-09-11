package com.tpa.junit5;

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

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TestOrderAlphabetic {

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
	static void init() {
		System.out.println(CLASS_SEPARATOR + " @BeforeAll");
	}

	@AfterAll
	static void after() {
		System.out.println(CLASS_SEPARATOR + " @AfterAll");
	}

	@Test
	public void TestMethodA() {
		System.out.println("\t\t\tRunning [" + "TestMethodA" + "]");

	}
	@DisplayName("TestMethodB")
	@Test
	public void TestMethodB(TestInfo testInfo) {
		System.out.println("\t\t\tRunning [" + testInfo.getDisplayName() + "]");
	}

	@Test
	public void TestMethodC(TestInfo testInfo) {
		System.out.println("\t\t\tRunning [" + testInfo.getDisplayName() + "]");

	}

	@DisplayName("TestMethodD")
	@Test
	public void TestMethodD(TestInfo testInfo) {
		System.out.println("\t\t\tRunning [" + testInfo.toString() + "]");

	}

}
