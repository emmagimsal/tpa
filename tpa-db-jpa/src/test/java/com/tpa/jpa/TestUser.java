package com.tpa.jpa;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tpa.jpa.entity.Usuario;
import com.tpa.jpa.repository.UsuarioRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {

	@Autowired
	private UsuarioRepository userRepo;

	public static String TEST_SEPARATOR = "\t\t >";
	public static String CLASS_SEPARATOR = "\t@";

	@Test
	public void testB() {

		Usuario usuario = new Usuario();
		usuario.setEmail("emailprueba@prueba.com.ar2");
		usuario.setNombre("nombre prueba");
		userRepo.save(usuario);
	}

	@Test
	public void testFindByCustom() {
		
		Usuario usuario = new Usuario();
		usuario.setEmail("emailprueba@prueba.com.ar");
		usuario.setNombre("nombre prueba");
		userRepo.save(usuario);

		List<Usuario> results = userRepo
				.finbyByCustom("emailprueba@prueba.com.ar");

		for (Usuario userDetails : results) {
			System.out.println(userDetails.toString());
		}

	}

	@Test
	public void testFindByNombre() {
		
		Usuario usuario = new Usuario();
		usuario.setEmail("emailprueba@prueba.com.ar");
		usuario.setNombre("nombre prueba");
		userRepo.save(usuario);
		

		List<Usuario> results = userRepo.findByNombre("nombre prueba");

		for (Usuario userDetails : results) {
			System.out.println(userDetails.toString());
		}

	}

	

}