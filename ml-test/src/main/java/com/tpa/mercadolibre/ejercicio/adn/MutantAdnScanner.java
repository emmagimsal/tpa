package com.tpa.mercadolibre.ejercicio.adn;

import java.util.ArrayList;
import java.util.List;

import com.tpa.mercadolibre.ejercicio.exception.ArrayNullException;
import com.tpa.mercadolibre.ejercicio.exception.MatrixNullException;

public class MutantAdnScanner {

	static String[] ALLOWED_CHARACTERS = new String[] { "A", "T", "G", "C" };

	static int MIN_OCURRENCES = 2;

	static int LENGTH = 4;

	public static boolean isMutant(String[] dna,boolean debug) throws ArrayNullException {
		boolean rta = false;
		
		validateArray(dna);

		List<String> words = getAllStrings(dna,debug);

		for (String string : words) {
			if(debug) System.out.print(string);

			for (int i = 0; i < ALLOWED_CHARACTERS.length; i++) {
				String code = ALLOWED_CHARACTERS[i];
				if (getNumOccurrences(string, code, debug)) {
					
					if(debug) System.out.print(" -> mutante con código " + code);
					
					return true;
				}

			}
			System.out.println();
		}

		return rta;
	}

	public static List<String> getAllStrings(String[] dna, boolean debug) {
		List<String> words = new ArrayList<>();

		char[][] m = convert(dna);
		
		

		words.addAll(scan(m,true));

		if (debug)
			displayMatrix(m.length, m, debug);
		
		m = rotateMatrix(m.length, m, debug);
		
		if (debug)
			displayMatrix(m.length, m, debug);

		words.addAll(scan(m,false));

		return words;
	}

	public static List<String> scan(char[][] matrix, boolean rowAndColumns) {
		List<String> words = new ArrayList<>();
		
		if(rowAndColumns) {
			words.addAll(getRows(matrix));
			words.addAll(getColumns(matrix));
		}
		
		words.addAll(getDiagonal(matrix, false));
		words.addAll(getDiagonal(matrix, true));

		return words;
	}

	public static List<String> getRows(char[][] matrix) {
		List<String> words = new ArrayList<>();

		for (int row = 0; row < matrix.length; row++) {
			String string = new String(matrix[row]);
			words.add(string);
		}

		return words;
	}

	public static List<String> getColumns(char[][] matrix) {
		List<String> words = new ArrayList<>();
		String string = null;

		for (int column = 0; column < matrix.length; column++) {
			string = "";
			for (int row = 0; row < matrix[column].length; row++) {
				string += matrix[row][column];
			}
			words.add(string);
		}

		return words;
	}

	public static List<String> getDiagonal(char[][] matrix, boolean upperDiagonal) {
		List<String> words = new ArrayList<>();
		String string = null;
		string = "";

		int from = 0;
		int to = matrix.length - LENGTH + 1;

		from = 0;
		/* evita recorrer la diagonal 2 veces */
		if (upperDiagonal)
			from++;

		to = matrix.length - LENGTH + 1;

		for (int iterador = from; iterador < to; iterador++) {
			string = "";
			for (int column = iterador; column < matrix.length; column++) {
				for (int row = 0; row < matrix[column].length; row++) {
					if (row + iterador == column) {
						if (upperDiagonal)
							string += matrix[row][column];
						else
							string += matrix[column][row];
						break;
					}
				}
			}
			words.add(string);
		}

		return words;
	}

	public static char[][] convert(String[] dna) {
		int dimArray = dna[0].length();
		char[][] matrix = new char[dimArray][dimArray];
		int pos = 0;

		for (String string : dna) {
			matrix[pos++] = string.toCharArray();
		}
		return matrix;
	}

	public static char[][] rotateMatrix(int N, char mat[][], boolean debug) {

		char[][] m2 = new char[mat.length][mat.length];

		for (int col = 0; col <= mat.length - 1; col++) {

			for (int row = mat.length - 1, ii = 0; row >= 0; row--, ii++) {

				char temp = mat[row][col];

				if (debug)
					System.out.println(
							"[" + row + "][" + col + "] " + temp + " -> [" + col + "][" + ii + "]" + mat[col][ii]);

				m2[col][ii] = mat[row][col];
			}
			if (debug)
				System.out.println("--------------------");
		}

		return m2;
	}

	public static void displayMatrix(int N, char mat[][], boolean debug) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				if (debug)
//					System.out.print("  [" + i + "][" + j + "]");
				System.out.print(" " + mat[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	public static boolean getNumOccurrences(String string, String code, boolean debug) {
		int lengthBefore = string.length();
		String finalString = string.replace(code, "");
		int lengthAfter = finalString.length();

		if (lengthBefore - lengthAfter >= LENGTH) {
			
			
			return true;
		}

		return false;
	}
	
	
	public boolean validateMatrix(char mat[][] ) throws MatrixNullException {
		
		if (mat==null) throw new MatrixNullException("Null Matrix ");
		
		return true;
	}
	
	public static boolean validateArray(String mat[] ) throws ArrayNullException {
		
		if (mat==null) throw new ArrayNullException("Null Matrix ");
		
		return true;
	}

}
