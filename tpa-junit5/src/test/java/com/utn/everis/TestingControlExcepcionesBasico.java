package com.utn.everis;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.utn.everis.codigoVerificador.CodigoVerificadorModulo11;

public class TestingControlExcepcionesBasico {

	@Test
	public void t1_testSinControlExcepciones() throws Exception {
		int cant_digitos = 0;

		Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificador(1234l, cant_digitos);

		org.junit.jupiter.api.Assertions.assertNull(rta);

	}

	@Test()
	public void t2_testControlExcepcionesCorrecto() throws Exception {
		int cant_digitos = 0;

		Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {

			Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificador(1234l, cant_digitos);

			org.junit.jupiter.api.Assertions.assertNull(rta);

		});

		System.out.println(exception.getMessage());

	}

	@Test()
	public void t3_controlExcepcionSinExcepcionLanzada() throws Exception {
		int cant_digitos = 2;

		Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {

			Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificador(23l, cant_digitos);

			org.junit.jupiter.api.Assertions.assertNotNull(rta);

		});

		System.out.println(exception.getMessage());

	}

	@Disabled
	@Test()
	public void t4_Ignorado() throws Exception {
		int cant_digitos = 2;

		Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {

			Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificador(23l, cant_digitos);

			org.junit.jupiter.api.Assertions.assertNotNull(rta);

		});

		System.out.println(exception.getMessage());

	}

	@Test
	public void t5_controlEjecucionCorrecto_DigitoVerificadorValor1() throws Exception {
		int cant_digitos = 1;

		Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificador(1234l, cant_digitos);

		org.junit.jupiter.api.Assertions.assertNotNull(rta);

	}

	@Test
	public void t6_control_ejecucionCorrecto_DigitoVerificadorValor4() throws Exception {
		int cant_digitos = 4;

		Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificador(1234l, cant_digitos);

		org.junit.jupiter.api.Assertions.assertNotNull(rta);

	}

	@Test
	public void t7_control_ejecucionCorrecto_prueba_innecesaria() throws Exception {
		int cant_digitos = 4;

		Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificador(1234l, cant_digitos);

		System.out.println(rta);

		Long entradaCtrl = Long.valueOf(rta.toString().substring(0, rta.toString().length() - cant_digitos));

		Long entradaCtrl_ = Long.valueOf(entradaCtrl.toString() + "5558");

		System.out.println(entradaCtrl_);

		org.junit.jupiter.api.Assertions.assertNotEquals(rta, entradaCtrl_);

	}

	@Test
	public void t8_control_codigo_verificacion_correcto() throws Exception {
		int cant_digitos = 4;

		Long numeroGenerado = CodigoVerificadorModulo11.generarNumeroConDigitoVerificador(1234l, cant_digitos);

		System.out.println(numeroGenerado);
		
		Long entradaCtrl_ = CodigoVerificadorModulo11.controlarCodigoVerificador(numeroGenerado, cant_digitos);
		
		System.out.println(entradaCtrl_);

		org.junit.jupiter.api.Assertions.assertEquals(numeroGenerado, entradaCtrl_);

	
		org.junit.jupiter.api.Assertions.assertTrue(CodigoVerificadorModulo11.checkCodigoVeridor(numeroGenerado, entradaCtrl_));
		
	}
	
	
	@Test
	public void t9_control_verificador() throws Exception {
		int cant_digitos = 4;

		Long numeroGenerado = CodigoVerificadorModulo11.generarNumeroConDigitoVerificador(1234l, cant_digitos);

		System.out.println(numeroGenerado);

		Long entradaCtrl = Long.valueOf(numeroGenerado.toString().substring(0, numeroGenerado.toString().length() - cant_digitos));

		Long entradaCtrl_ = Long.valueOf(entradaCtrl.toString() + "5558");

		System.out.println(entradaCtrl_);

		org.junit.jupiter.api.Assertions.assertEquals(numeroGenerado, entradaCtrl_);
		
		org.junit.jupiter.api.Assertions.assertTrue(CodigoVerificadorModulo11.checkCodigoVeridor(numeroGenerado, entradaCtrl_));

	}

	
	@DisplayName("t10_control_exhaustivo_numero_entrada")
	@RepeatedTest(value = 10, name = "{displayName} - iteracion {currentRepetition} of {totalRepetitions}")
	public void test_A1(TestInfo testInfo, RepetitionInfo repetitionInfo) throws Exception {
		int cant_digitos = 1;

		Long numeroGenerado = CodigoVerificadorModulo11
				.generarNumeroConDigitoVerificador(Long.valueOf(repetitionInfo.getCurrentRepetition()), cant_digitos);

		System.out.println(numeroGenerado);

	}
	
	/*
	 * crear una condicion de control en la clase CodigoVerificadorModulo11.generarNumeroConDigitoVerificador "entrada.toString().length() < cantDigitos" 
	 * capaz de lanzar una excepcion con el texto ""La cantidad de digitos verificadores no debe superar la longitud del numero de entrada""
	 * y realizar los test necesarios para asegurar que dicha excepcion sea lanzada en algun momento .
	 * 
	 * 
	 * Ejercicio crear un metodo similiar "checkCodigoVeridor" con manejo de excepciones y 
	 * crear los test correspondiente test
	 * 
	 * crear test para realizar un control de exhaustivo pero logico de la cantidad de digitos verificadores
	 * 
	 * */
	
	

}
