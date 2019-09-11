package com.tpa.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tpa.junit.exception.ExceptionPrueba;
import com.tpa.junit.validation.ValidatorDU;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class TpaJunitApplicationTests {

	public static String TEST_SEPARATOR = "\t\t >";
	public static String CLASS_SEPARATOR = "\t@";


	@Ignore("Not yet implemented")
	@Test
	public void test1() {
	}

	@Test
	public void testCase1() {
		System.out.println("\t\t\ttestCase1!!");
	}

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

	@Ignore()
	@Test(timeout = 500)
	public void testTimeout() {

		while (true) {

		}
	}

	@Test(expected = ExceptionPrueba.class)
	public void testCheckIllegalArgumentException() throws ExceptionPrueba {

		Persisitir p = new Persisitir();
		p.lazarException();
//		throw new IllegalArgumentException("error");

	}
	
	
	@Test(expected = Exception.class)
	public void testPErsonaDuplicada() throws ExceptionPrueba {
		
		
//		ValidatorDU.validadDuplicados(p1, p4);
		
	}

	@BeforeClass
	public static void beforeClass() {
		System.out.println(CLASS_SEPARATOR + " Before Testing Class");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println(CLASS_SEPARATOR + " After Testing Class");
	}

}
