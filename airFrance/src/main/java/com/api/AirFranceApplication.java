package com.api;

import com.api.entities.User;
import com.api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootApplication
public class AirFranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirFranceApplication.class, args);
	}
	ZoneId defaultZoneId = ZoneId.systemDefault();
	LocalDate localdateUser1= LocalDate.of(1976 ,2,15);
	LocalDate localdateUser2= LocalDate.of(1981 ,10,01);
	LocalDate localdateUser3= LocalDate.of(1985 ,9,26);
	LocalDate localdateUser4= LocalDate.of(1965 ,5,9);
	@Bean
	CommandLineRunner start(UserRepository userRepository) {

		return args -> {
			userRepository.save(new User(null, "Pierre Michel", localdateUser1, "France", "0612236369", "Homme"));
			userRepository.save(new User(null, "Nadir Chelbi", localdateUser2, "France", "0645566996", "Homme"));
			userRepository.save(new User(null, "celine dupond", localdateUser3, "France", "0645566996", "Femme"));
			userRepository.save(new User(null, "martine Paul", localdateUser4, "France", "0612233663", "Femme"));
			userRepository.findAll().forEach(user -> {
				System.out.println(user.toString());
			});
		};
	}}
