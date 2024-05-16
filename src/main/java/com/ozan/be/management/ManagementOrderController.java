package com.ozan.be.management;

import com.ozan.be.common.BaseController;
import com.ozan.be.common.dtos.BasicReponseDTO;
import com.ozan.be.order.OrderService;
import com.ozan.be.order.domain.OrderSearchFilter;
import com.ozan.be.order.domain.OrderStatus;
import com.ozan.be.order.dtos.OrderResponseDTO;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/management/orders")
@AllArgsConstructor
@RestController
public class ManagementOrderController extends BaseController {
  private final OrderService orderService;

  @GetMapping("/")
  public ResponseEntity<Page<OrderResponseDTO>> getAllOrders(
      @PageableDefault(size = 5) Pageable pageable,
      @ParameterObject OrderSearchFilter orderSearchFilter) {
    Page<OrderResponseDTO> responseDTOS =
        orderService.getAllOrders(pageable, orderSearchFilter.getPredicate());
    return ResponseEntity.ok(responseDTOS);
  }

  @PutMapping("/{id}/{status}")
  public ResponseEntity<BasicReponseDTO> updateOrderStatus(
      @PathVariable("id") UUID id, @PathVariable("status") OrderStatus status) {
    orderService.updateOrderStatus(id, status);
    return ResponseEntity.ok(new BasicReponseDTO(true));
  }
}
