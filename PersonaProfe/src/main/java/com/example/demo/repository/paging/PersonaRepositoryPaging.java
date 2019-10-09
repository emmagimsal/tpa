package com.example.demo.repository.paging;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entities.Persona;

public interface PersonaRepositoryPaging extends PagingAndSortingRepository<Persona, Long> {

	@Query("from Persona u order by u.nombre asc ")
	Page<Persona> findAllOrderByNombre(Pageable pag);

	Page<Persona> findByNombre(Pageable pag);

	Page<Persona> findByNombre(Pageable pag, String nombre);

	Page<Persona> findByApellido(Pageable pag, String apellido);

	Page<Persona> findByDni(Pageable pag, Integer dni);

}
