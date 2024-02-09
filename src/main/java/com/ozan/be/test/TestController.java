package com.ozan.be.test;

import com.ozan.be.order.Order;
import com.ozan.be.user.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@NoArgsConstructor
@RestController
public class TestController {

  @Autowired private UserRepository userRepository;

  @GetMapping("t")
  public List<Order> test() {
    return userRepository.findByEmail("kadircan.bozkurt@mail.com").get().getOrders();
    // return userRepository.findAll();
  }

  @GetMapping("z")
  public static String priv() {
    return "PRIV ENDPOINT Helloooo there!";
  }

  @GetMapping("management")
  @PreAuthorize("hasRole('MANAGER')")
  public static String management() {
    return "management::get";
  }
}
