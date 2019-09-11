package com.tpa.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class TestAssertion {

	public static String TEST_SEPARATOR = "\t\t >";
	public static String CLASS_SEPARATOR = "\t@";

	@Rule
	public TestName testCaseName = new TestName();

	private final String[] words = { "lunes", "martes", "miércoles", "jueves", "viernes", "sabado", "domingo", "enero",
			"febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "noviembre", "diciembre",
			"lunes" };

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

	public List<String> getStringByContent(String name) {
		List<String> res = new ArrayList<String>();
		if (name != null) {
			for (String word : words) {
				if (word.toUpperCase().contains(name.toUpperCase())) {
					res.add(word);
				}
			}
		}
		return res;
	}

	@Ignore
	@Test
	public void TestCheckByContent() {
		System.out.println("\t\t\tRunning ["+testCaseName.getMethodName()+"]");
		
		List<String> words_ = getStringByContent("lunes");
		if (words_.size() != 2) {
			fail("Deberían haber 2 elementos");
		} else if (!words_.contains("agosto2")) {
			fail("Agosto2 no existe en la colección de datos");
		} else if (!words_.contains("septiembre")) {
			fail("Septiembre no Existe");
		}
	}

//	@Ignore
	@Test
	@junitparams.Parameters({ "lunes,2", "martes,1", "ener0,10" })
	public void getStringResult(String word, int resultadosEsperados) {
		
		System.out.println("\t\t\tRunning ["+testCaseName.getMethodName()+"] name ="+word +" resultados esperados "+ resultadosEsperados);
		
		List<String> usersByName = getStringByContent(word);
		assertEquals(resultadosEsperados, usersByName.size());
	}
}
