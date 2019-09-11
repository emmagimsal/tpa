package com.tpa.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class TestOrderAlphabetic {

	public static String TEST_SEPARATOR = "\t\t >";
	public static String CLASS_SEPARATOR = "\t@";

	@Rule
	public TestName testCaseName = new TestName();


	@Before
	public void beforeEachTest() {
		System.out.println(TEST_SEPARATOR + "Before");
	}

	/**
	 * Tears down the test fixture. (Called after every test case method.)
	 */
	@After
	public void afterEachTest() {
		System.out.println(TEST_SEPARATOR + "After");
	}

	@BeforeClass
	public static void beforeClass() {
		System.out.println(CLASS_SEPARATOR + " Before Testing Class");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println(CLASS_SEPARATOR + " After Testing Class");
	}


	@Test
	public void TestMethodZZ() {
		System.out.println("\t\t\tRunning ["+testCaseName.getMethodName()+"]");
		
	}
	
	@Test
	public void TestMethodAA() {
		System.out.println("\t\t\tRunning ["+testCaseName.getMethodName()+"]");
		
	}
	
	@Test
	public void TestMethodBB() {
		System.out.println("\t\t\tRunning ["+testCaseName.getMethodName()+"]");
		
	}
	
	@Test
	public void TestMethodCC() {
		System.out.println("\t\t\tRunning ["+testCaseName.getMethodName()+"]");
		
	}
}
