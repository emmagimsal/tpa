package com.tpa.junit;

import com.tpa.junit.exception.ExceptionPrueba;

public class Persisitir {
	public void lazarException() throws ExceptionPrueba {
		throw new ExceptionPrueba("....");
	}
}
