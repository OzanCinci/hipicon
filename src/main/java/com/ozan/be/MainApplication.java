package com.ozan.be;

import static com.ozan.be.user.domain.Role.*;

import com.ozan.be.auth.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MainApplication {

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(AuthenticationService service) {
    return args -> {
      /*
      RegisterRequestDTO requestDTO = new RegisterRequestDTO();
      requestDTO.setEmail("admin@mail.com");
      requestDTO.setFirstName("Admin");
      requestDTO.setLastName("Admin");
      requestDTO.setPassword("password");
      requestDTO.setRole(ADMIN);

      System.out.println("Admin token: " + service.register(requestDTO).getAccessToken());

      requestDTO = new RegisterRequestDTO();
      requestDTO.setEmail("hipicon");
      requestDTO.setFirstName("Admin");
      requestDTO.setLastName("Admin");
      requestDTO.setPassword("password");
      requestDTO.setRole(ADMIN);
      System.out.println("hipicon token: " + service.register(requestDTO).getAccessToken());

      requestDTO = new RegisterRequestDTO();
      requestDTO.setEmail("ozan.cinci@mail.com");
      requestDTO.setFirstName("Ozan");
      requestDTO.setLastName("Cinci");
      requestDTO.setPassword("password");
      requestDTO.setRole(USER);
      System.out.println("Regular User token: " + service.register(requestDTO).getAccessToken());

       */
    };
  }
}
