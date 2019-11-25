package com.tpa.mercadolibre.ejercicio.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dna implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int hascode;
	
	private int mutant;/*0=no, 1=yes*/

	public int getMutant() {
		return mutant;
	}

	public void setMutant(int mutant) {
		this.mutant = mutant;
	}

	public int getHascode() {
		return hascode;
	}

	public void setHascode(int hascode) {
		this.hascode = hascode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result += prime * result + ((dnaCode == null) ? 0 : dnaCode.hashCode());
		result += prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dna other = (Dna) obj;
		if (dnaCode == null) {
			if (other.dnaCode != null)
				return false;
		} else if (!dnaCode.equals(other.dnaCode))
			return false;
		if (id != other.id)
			return false;
		return true;
	}


	@ElementCollection
	private List<String> dnaCode=new ArrayList<>();
	
	
	public List<String> getDnaCode() {
		return dnaCode;
	}

	public void setDnaCode(List<String> dnaCode) {
		this.dnaCode = dnaCode;
	}

	@Override
	public String toString() {
		return "Dna [id=" + id + ", dnaCode=" + dnaCode + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
