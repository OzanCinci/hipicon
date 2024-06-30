package com.ozan.be.product;

import com.ozan.be.common.BaseController;
import com.ozan.be.common.dtos.BasicReponseDTO;
import com.ozan.be.product.domain.ProductSearchFilter;
import com.ozan.be.product.domain.ProductStatus;
import com.ozan.be.product.dtos.DashboardResponseDTO;
import com.ozan.be.product.dtos.ProductRequestDTO;
import com.ozan.be.product.dtos.ProductResponseDTO;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController extends BaseController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductResponseDTO> createProduct(
      @Valid @ModelAttribute ProductRequestDTO productRequestDTO) {
    UUID userId = getCurrentUser().getId();
    ProductResponseDTO responseDTO = productService.createProduct(userId, productRequestDTO);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping
  public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(
      @PageableDefault(size = 5) Pageable pageable,
      @ParameterObject ProductSearchFilter productSearchFilter) {
    Page<ProductResponseDTO> response =
        productService.getAllProducts(pageable, productSearchFilter.getPredicate());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID id) {
    ProductResponseDTO responseDTO = productService.getProductById(id);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/low-stock")
  public ResponseEntity<Page<ProductResponseDTO>> getLowStockProducts(
      @PageableDefault(size = 5) Pageable pageable) {
    UUID userId = getCurrentUser().getId();
    Page<ProductResponseDTO> responseDTOS = productService.getLowStockProducts(pageable, userId);
    return ResponseEntity.ok(responseDTOS);
  }

  @GetMapping("/dashboard")
  public ResponseEntity<DashboardResponseDTO> getDashboardProducts() {
    UUID userId = getCurrentUser().getId();
    DashboardResponseDTO responseDTO = productService.getDashboardProducts(userId);
    return ResponseEntity.ok(responseDTO);
  }

  @PutMapping("/{id}/approve")
  public ResponseEntity<BasicReponseDTO> approveProduct(@PathVariable UUID id) {
    productService.approveProduct(id);
    return ResponseEntity.ok(new BasicReponseDTO(true));
  }

  @PutMapping("/{id}/status/{status}")
  public ResponseEntity<BasicReponseDTO> changeStatus(
      @PathVariable UUID id, @PathVariable ProductStatus status) {
    UUID userId = getCurrentUser().getId();
    productService.changeStatus(userId, id, status);
    return ResponseEntity.ok(new BasicReponseDTO(true));
  }
}
