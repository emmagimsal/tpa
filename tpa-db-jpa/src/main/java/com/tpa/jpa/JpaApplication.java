package com.tpa.jpa;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tpa.jpa.entity.Usuario;
import com.tpa.jpa.repository.UsuarioRepository;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository userRepo) {
		return args -> {
			
			int cantidad=35;
			
			userRepo.deleteAll();
			
			LongStream.range(1, cantidad)
					.mapToObj(i -> {

				    	Usuario usuario=new Usuario();
				    	usuario.setEmail(i+"@"+i*2+".com.ar");
				    	usuario.setNombre("nombre"+i);
				    	
				    	userRepo.save(usuario);
						return usuario;
					})
					.map(v -> userRepo.save(v))
					.forEach(System.out::println)
					;
			
	};
	
	}
	

}