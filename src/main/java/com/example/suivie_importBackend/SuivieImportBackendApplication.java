package com.example.suivie_importBackend;

import com.example.suivie_importBackend.service.SeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class SuivieImportBackendApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext context;
	@Autowired
	SeederService seederService;

	public static void main(String[] args) {
		SpringApplication.run(SuivieImportBackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

    @Override
    public void run(String... args) {
        String seederOption = "seed";

        if (args.length == 1 && seederOption.equals(args[0])) {
            seederService.seed();
            System.exit(0);
        }
    }
}
