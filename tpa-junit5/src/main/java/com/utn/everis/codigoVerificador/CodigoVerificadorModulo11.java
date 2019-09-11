package com.utn.everis.codigoVerificador;

import com.utn.everis.exception.CodigoVerificadorInvalidoException;
import com.utn.everis.exception.CodigoVerificadorUnexpectedException;
import com.utn.everis.exception.NumeroEntradaNulaException;

public class CodigoVerificadorModulo11 {

	public String invertirCadena(String cadena) {
		String cadenaInvertida = "";
		for (int x = cadena.length() - 1; x >= 0; x--) {
			cadenaInvertida = cadenaInvertida + cadena.charAt(x);
		}
		return cadenaInvertida;
	}

	public int obtenerSumaPorDigitos(String cadena) {
		int pivote = 2;
		int longitudCadena = cadena.length();
		int cantidadTotal = 0;
		int b = 1;
		for (int i = 0; i < longitudCadena; i++) {
			if (pivote == 8) {
				pivote = 2;
			}
			int temporal = Integer.parseInt("" + cadena.substring(i, b));
			b++;
			temporal *= pivote;
			pivote++;
			cantidadTotal += temporal;
		}
		cantidadTotal = 11 - cantidadTotal % 11;
		return cantidadTotal;
	}

	public static Long generarNumeroConDigitoVerificador(Long entrada, int cantDigitos) throws Exception {

		if (cantDigitos < 1)
			throw new Exception("Debe al menos Tener un digito verificador para permitir su posterior verificacion");

		if (entrada == null)
			throw new Exception("El Numero de Entrada no puede ser un valor Nulo");


		CodigoVerificadorModulo11 a = new CodigoVerificadorModulo11();
		Long entradaCDigit = null;

		for (int i = 0; i < cantDigitos; i++) {
			entradaCDigit = entrada.longValue();
			int numero = a.obtenerSumaPorDigitos(entradaCDigit.toString());
			entrada = Long.valueOf(entradaCDigit.toString() + Long.valueOf(numero));
		}

		return entrada;
	}

	public static Long generarNumeroConDigitoVerificadorExceptions(Long entrada, int cantDigitos) throws Exception {

		if (cantDigitos < 1)
			throw new CodigoVerificadorInvalidoException(
					"Debe al menos Tener un digito verificador para permitir su posterior verificacion");

		if (entrada == null)
			throw new NumeroEntradaNulaException("El Numero de Entrada no puede ser un valor Nulo");

		if (entrada.toString().length() < cantDigitos)
			throw new CodigoVerificadorUnexpectedException(
					"La cantidad de digitos verificadores no debe superar la longitud del numero de entrada");

		CodigoVerificadorModulo11 a = new CodigoVerificadorModulo11();
		Long entradaCDigit = null;

		for (int i = 0; i < cantDigitos; i++) {
			entradaCDigit = entrada.longValue();
			int numero = a.obtenerSumaPorDigitos(entradaCDigit.toString());
			entrada = Long.valueOf(entradaCDigit.toString() + Long.valueOf(numero));
		}

		return entrada;
	}

	public static boolean checkCodigoVeridor(Long entrada, Long entradactrl) throws Exception {

		if (entrada - entradactrl == 0)
			return true;

		else
			return false;
	}

	public static Long controlarCodigoVerificador(Long nuEntrada, int cant_digitos) throws Exception {

		Long numeroDeControl = Long
				.valueOf(nuEntrada.toString().substring(0, nuEntrada.toString().length() - cant_digitos - 1));

		Long entradaCtrl_ = generarNumeroConDigitoVerificador(numeroDeControl, cant_digitos);

		return entradaCtrl_;

	}

}