package com.fpsoluctionstechs.hortfruitonline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fpsoluctionstechs.hortfruitonline.model.Usuario;
import com.fpsoluctionstechs.hortfruitonline.respository.UsuarioRepository;
@SpringBootApplication
public class HortfruitOnlineApplication {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(HortfruitOnlineApplication.class, args);
		
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
			
			
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			Usuario usuario = Usuario.builder()
					.username("mauro")
					.password(bCryptPasswordEncoder.encode("mauro"))
					.build();
			usuarioRepository.save(usuario);
			
			
			
			
			
			
			
			
			
			
		};
		
	
	
	}
	
	
	
	


}
