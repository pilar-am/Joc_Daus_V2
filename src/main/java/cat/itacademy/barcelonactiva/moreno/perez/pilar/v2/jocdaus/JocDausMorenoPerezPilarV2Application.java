package cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class JocDausMorenoPerezPilarV2Application {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JocDausMorenoPerezPilarV2Application.class, args);
		
	}

}
