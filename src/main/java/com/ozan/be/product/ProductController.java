package com.ozan.be.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {

  private final ProductService productService;
}
