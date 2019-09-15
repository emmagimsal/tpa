package com.tpa.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.tpa.jpa.entity.Usuario;


public interface UsuarioRepositoryPaging extends PagingAndSortingRepository<Usuario, Long> {
	@Query("from Usuario u order by u.id asc ")
	Page<Usuario> findAllUsuarios(Pageable pag);  
	
}
