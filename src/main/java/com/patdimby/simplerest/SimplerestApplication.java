package com.patdimby.simplerest;

import com.patdimby.simplerest.model.User;
import com.patdimby.simplerest.model.UserRole;
import com.patdimby.simplerest.repository.UserRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT Authorization header using the Bearer scheme"
)
@SpringBootApplication
public class SimplerestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplerestApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .name("bearerAuth")
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    @Bean
    CommandLineRunner initAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.email:patdimby@outlook.fr}") String adminEmail,
            @Value("${app.admin.username:patdimby}") String adminUsername,
            @Value("${app.admin.firstname:Patrick}") String adminFirstName,
            @Value("${app.admin.lastname:Dimbisoa}") String adminLastName,
            @Value("${app.admin.password:Masterkey1}") String adminPassword
    ) {
        return args -> {
            if (userRepository.findByEmail(adminEmail).isPresent()) {
                System.out.println("✅ Admin user already exists: " + adminEmail);
                return;
            }

            User admin = new User();
            admin.setFirstName(adminFirstName);              // ✅ obligatoire
            admin.setLastName(adminLastName);                // optionnel
            admin.setUsername(adminUsername);
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(UserRole.ROLE_ADMIN);

            userRepository.save(admin);
            System.out.println("✅ Created admin user: " + adminEmail);
        };
    }
}
