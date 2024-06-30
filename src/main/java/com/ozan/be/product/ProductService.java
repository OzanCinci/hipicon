package com.ozan.be.product;

import com.ozan.be.awsServices.S3Service;
import com.ozan.be.customException.types.BadRequestException;
import com.ozan.be.customException.types.DataNotFoundException;
import com.ozan.be.product.domain.Product;
import com.ozan.be.product.domain.ProductStatus;
import com.ozan.be.product.domain.QProduct;
import com.ozan.be.product.dtos.DashBoardView;
import com.ozan.be.product.dtos.DashboardResponseDTO;
import com.ozan.be.product.dtos.ProductRequestDTO;
import com.ozan.be.product.dtos.ProductResponseDTO;
import com.ozan.be.user.UserService;
import com.ozan.be.user.domain.User;
import com.ozan.be.utils.ModelMapperUtils;
import com.ozan.be.utils.PageableUtils;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

  private final S3Service s3Service;
  private final UserService userService;
  private final ProductRepository productRepository;

  private Product findById(UUID id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new DataNotFoundException("Product not found with id: " + id));
  }

  private Product findByIdAndUserId(UUID id, UUID userId) {
    return productRepository
        .findByIdAndUserId(id, userId)
        .orElseThrow(() -> new DataNotFoundException("Product not found with id: " + id));
  }

  private static ProductResponseDTO getProductResponseDTO(
      Product product, String email, String fullName) {
    ProductResponseDTO responseDTO = ModelMapperUtils.map(product, ProductResponseDTO.class);
    responseDTO.setSellerEmail(email);
    responseDTO.setSellerName(fullName);
    return responseDTO;
  }

  public ProductResponseDTO getProductById(UUID id) {
    Product product = findById(id);
    User user = product.getUser();
    String email = user.getEmail();
    String fullName = user.getFirstName() + " " + user.getLastName();
    return getProductResponseDTO(product, email, fullName);
  }

  public Page<ProductResponseDTO> getAllProducts(Pageable pageable, Predicate predicate) {
    Pageable finalPageable = PageableUtils.prepareAuditSorting(pageable);
    Page<Product> productPage = productRepository.findAll(predicate, finalPageable);

    List<ProductResponseDTO> productResponseDTOS =
        productPage.getContent().stream()
            .map(
                p -> {
                  ProductResponseDTO dto = ModelMapperUtils.map(p, ProductResponseDTO.class);
                  User user = p.getUser();
                  dto.setSellerEmail(user.getEmail());
                  dto.setSellerName(user.getFirstName() + " " + user.getLastName());
                  return dto;
                })
            .toList();

    return new PageImpl<>(
        productResponseDTOS, productPage.getPageable(), productPage.getTotalElements());
  }

  public Page<ProductResponseDTO> getLowStockProducts(Pageable pageable, UUID userId) {
    Pageable finalPageable = PageableUtils.prepareAuditSorting(pageable);
    Predicate predicate =
        ExpressionUtils.allOf(QProduct.product.user.id.eq(userId), QProduct.product.quantity.lt(2));
    Page<Product> productPage = productRepository.findAll(predicate, finalPageable);
    List<ProductResponseDTO> productResponseDTOS =
        ModelMapperUtils.mapAll(productPage.getContent(), ProductResponseDTO.class);
    return new PageImpl<>(
        productResponseDTOS, productPage.getPageable(), productPage.getTotalElements());
  }

  public DashboardResponseDTO getDashboardProducts(UUID userId) {
    DashBoardView dashBoardView = productRepository.getDashboardStatistics(userId);
    return ModelMapperUtils.map(dashBoardView, DashboardResponseDTO.class);
  }

  @Transactional
  public ProductResponseDTO createProduct(UUID userId, ProductRequestDTO productRequestDTO) {
    User user = userService.getUserById(userId);
    List<String> imageUrls = s3Service.uploadImages(productRequestDTO.getImages());

    Product product = ModelMapperUtils.map(productRequestDTO, Product.class);
    product.setUser(user);
    product.setImageUrls(imageUrls);
    product.setStock(Boolean.TRUE.equals(productRequestDTO.getQuantity() > 0));

    Product savedProduct = productRepository.save(product);

    return getProductResponseDTO(
        savedProduct, user.getEmail(), user.getFirstName() + " " + user.getLastName());
  }

  @Transactional
  public void approveProduct(UUID id) {
    // restricted to only admin users via security configuration
    Product product = findById(id);
    product.setStatus(ProductStatus.ACTIVE);
    productRepository.save(product);
  }

  @Transactional
  public void changeStatus(UUID userId, UUID id, ProductStatus status) {
    // only the owner of the product can change the status

    if (ProductStatus.PENDING.equals(status)) {
      throw new DataNotFoundException("Product status cannot be set as PENDING");
    }

    Product product = findByIdAndUserId(id, userId);

    if (ProductStatus.PENDING.equals(product.getStatus())) {
      throw new BadRequestException(
          "Products have to be approved by an admin before changing its status.");
    }

    product.setStatus(status);
    productRepository.save(product);
  }
}
