package com.tpa.junit.validation;

import com.tpa.junit.entity.Persona;

public class ValidatorDU {
	
	public static String MSJ_DU_DUPLICADO="ESTIMADO USUARIO AMBAS PERSONAS POSEEN IDÉNTICO NUMERO ÚNICO";
	

	public static boolean validadDuplicados(Persona p1, Persona p4) throws Exception {

		if (p1.getNumero().trim().equalsIgnoreCase(p4.getNumero().trim())) {
			return true;
		}

		else

			throw new Exception(MSJ_DU_DUPLICADO);
	}

}
