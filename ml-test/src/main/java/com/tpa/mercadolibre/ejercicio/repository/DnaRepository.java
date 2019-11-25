package com.tpa.mercadolibre.ejercicio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tpa.mercadolibre.ejercicio.entity.Dna;

public interface DnaRepository extends  JpaRepository<Dna, Integer>{

	@Query("from Dna d where d.hascode= :hash ")
	Optional<Dna> findByHashCode(@Param("hash")  int hash);
	
	@Query("select count (*) as mutant_count from Dna d where d.mutant= 1 ")
	Optional<Object> getMutantCount();
	
	@Query("select count (*) as no_mutant_count from Dna d where d.mutant= 0 ")
	Optional<Object> getNoMutantCount();
	
	
	@Query("select  avg (mutant) as avg_mutant from Dna d where d.mutant=0 or d.mutant=1")
	Optional<Object> getAverage();
	
	
}
