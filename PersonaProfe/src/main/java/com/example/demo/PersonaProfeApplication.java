package com.example.demo;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entities.Persona;
import com.example.demo.repository.PersonaRepository;

@SpringBootApplication
public class PersonaProfeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonaProfeApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PersonaRepository personaRepo) {
		return args -> {

			int cantidad = 35;

			personaRepo.deleteAll();
			LongStream.range(1, cantidad).mapToObj(i -> {
			
				Persona persona = new Persona();
				persona.setApellido("Apell" + i);
				persona.setNombre("nom" + i);
				persona.setDni( Integer.valueOf( Long.valueOf(i).toString()));
				personaRepo.save(persona);
				return persona;
			}).map(v -> personaRepo.save(v)).forEach(System.out::println);

		};

	}

}
