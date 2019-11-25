package com.tpa.mercadolibre.ejercicio.dto;

public class ADN {
//	ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}

	long count_mutant_dna;
	long count_human_dna;
	double ratio;
	public long getCount_mutant_dna() {
		return count_mutant_dna;
	}
	public void setCount_mutant_dna(long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	public long getCount_human_dna() {
		return count_human_dna;
	}
	public void setCount_human_dna(long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}


}
