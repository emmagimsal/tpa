package com.tpa.junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodLengthOrderer.class)
public class TestOrder2 {

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
	public void test_A1() {
		System.out.println("\t\t\tRunning [" + "TestMethodA" + "]");

	}
	@DisplayName("test_AA2")
	@Test
	public void test_AA2(TestInfo testInfo) {
		System.out.println("\t\t\tRunning [" + testInfo.getDisplayName() + "]");
	}

	@Test
	public void test_AAAAA4(TestInfo testInfo) {
		System.out.println("\t\t\tRunning [" + testInfo.getDisplayName() + "]");

	}

	@DisplayName("test_AAAA3")
	@Test
	public void test_AAAA3(TestInfo testInfo) {
		System.out.println("\t\t\tRunning [" + testInfo.toString() + "]");

	}

}
