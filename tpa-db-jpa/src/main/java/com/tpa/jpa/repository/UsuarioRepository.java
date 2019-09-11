package com.tpa.jpa.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tpa.jpa.entity.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	
	 @Query("SELECT u FROM Usuario u  WHERE u.email  = :email ")
	 List<Usuario> finbyByCustom(@Param("email") String email);
	 
	 
	 List<Usuario> findByNombre(@Param("nombre") String nombre);
	 
}