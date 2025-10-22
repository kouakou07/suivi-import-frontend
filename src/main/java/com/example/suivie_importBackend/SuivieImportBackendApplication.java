package com.example.suivie_importBackend;

import com.example.suivie_importBackend.service.SeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class SuivieImportBackendApplication {

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

	@Bean
    public CommandLineRunner run(SeederService seederService) {
        return args -> {
            if (seederService.countUsers() == 0) {
                System.out.println("La base de données est vide. Lancement du seeder...");
                seederService.seed();
                System.out.println("Seeder terminé avec succès !");
            } else {
                System.out.println("La base de données contient déjà des utilisateurs. Seeder non exécuté.");
            }
        };
    }
}
