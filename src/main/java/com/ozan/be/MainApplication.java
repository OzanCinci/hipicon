package com.ozan.be;

import static com.ozan.be.user.domain.Role.*;

import com.ozan.be.auth.AuthenticationService;
import com.ozan.be.auth.dtos.RegisterRequestDTO;
import com.ozan.be.order.*;
import com.ozan.be.product.Product;
import com.ozan.be.product.ProductRepository;
import com.ozan.be.review.ReviewService;
import com.ozan.be.user.UserRepository;
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
  public CommandLineRunner commandLineRunner(
      AuthenticationService service,
      UserRepository userRepository,
      OrderService orderService,
      ProductRepository productRepository,
      OrderItemRepository orderItemRepository,
      ReviewService reviewService) {
    return args -> {
      RegisterRequestDTO requestDTO = new RegisterRequestDTO();
      requestDTO.setEmail("admin@mail.com");
      requestDTO.setFirstName("Admin");
      requestDTO.setLastName("Admin");
      requestDTO.setPassword("password");
      requestDTO.setRole(ADMIN);

      System.out.println("Admin token: " + service.register(requestDTO).getAccessToken());

      requestDTO = new RegisterRequestDTO();
      requestDTO.setEmail("manager@mail.com");
      requestDTO.setFirstName("Admin");
      requestDTO.setLastName("Admin");
      requestDTO.setPassword("password");
      requestDTO.setRole(MANAGER);
      System.out.println("Manager token: " + service.register(requestDTO).getAccessToken());

      requestDTO = new RegisterRequestDTO();
      requestDTO.setEmail("kadircan.bozkurt@mail.com");
      requestDTO.setFirstName("Kadircan");
      requestDTO.setLastName("Bozkurt");
      requestDTO.setPassword("password");
      requestDTO.setRole(USER);
      System.out.println("Regular User token: " + service.register(requestDTO).getAccessToken());

      Product product = new Product();
      product.setName("car");
      product.setImageUrl("something.jpg");
      product.setCategory("item");
      product.setDescription("description field");
      productRepository.saveAndFlush(product);
    };
  }
}
