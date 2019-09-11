package com.utn.everis;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.utn.everis.codigoVerificador.CodigoVerificadorModulo11;
import com.utn.everis.exception.CodigoVerificadorInvalidoException;
import com.utn.everis.exception.CodigoVerificadorUnexpectedException;
import com.utn.everis.exception.NumeroEntradaNulaException;

public class TestingConControlExcepciones {

	@DisplayName("Test Assert -> Control excepcion CodigoVerificadorUnexpectedException Correcto")
	@Test()
	public void t1() throws Exception {
		int cant_digitos = 2;

		CodigoVerificadorUnexpectedException exception = org.junit.jupiter.api.Assertions
				.assertThrows(CodigoVerificadorUnexpectedException.class, () -> {
					Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificadorExceptions(2l, cant_digitos);

					org.junit.jupiter.api.Assertions.assertNotNull(rta);

				});

		System.out.println(exception.getMessage());

	}

	@DisplayName("Test Assert -> Control excepcion NumeroEntradaNulaException Correcto")
	@Test()
	public void t2() throws Exception {
		int cant_digitos = 2;

		NumeroEntradaNulaException exception = org.junit.jupiter.api.Assertions
				.assertThrows(NumeroEntradaNulaException.class, () -> {
					Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificadorExceptions(null,
							cant_digitos);

					org.junit.jupiter.api.Assertions.assertNotNull(rta);

				});

		System.out.println(exception.getMessage());

	}

	@DisplayName("Test Assert -> Sin manejo de Excepción")
	@Test
	public void t3() throws Exception {
		int cant_digitos = 2;

		Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificadorExceptions(null, cant_digitos);

		org.junit.jupiter.api.Assertions.assertNotNull(rta);

	}

	@DisplayName("Test Assert -> Correcto")
	@Test()
	public void t4() throws Exception {

		int cant_digitos = 0;

		CodigoVerificadorInvalidoException exception = org.junit.jupiter.api.Assertions
				.assertThrows(CodigoVerificadorInvalidoException.class, () -> {
					Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificadorExceptions(1l, cant_digitos);

					org.junit.jupiter.api.Assertions.assertNotNull(rta);

				});

		org.junit.jupiter.api.Assertions.assertEquals(
				"Debe al menos Tener un digito verificador para permitir su posterior verificacion",
				exception.getMessage());

	}

	@DisplayName("Test Assert -> Texto Excepción no esperado")
	@Test()
	public void t5() throws Exception {

		int cant_digitos = 0;

		CodigoVerificadorInvalidoException exception = org.junit.jupiter.api.Assertions
				.assertThrows(CodigoVerificadorInvalidoException.class, () -> {
					Long rta = CodigoVerificadorModulo11.generarNumeroConDigitoVerificadorExceptions(1l, cant_digitos);

					org.junit.jupiter.api.Assertions.assertNotNull(rta);

				});

		org.junit.jupiter.api.Assertions.assertEquals("234tt", exception.getMessage());

	}

}
