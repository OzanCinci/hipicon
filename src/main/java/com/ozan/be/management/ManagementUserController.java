package com.ozan.be.management;

import com.ozan.be.common.BaseController;
import com.ozan.be.user.UserService;
import com.ozan.be.user.domain.UserSearchFilter;
import com.ozan.be.user.dtos.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/management/users")
@AllArgsConstructor
@RestController
public class ManagementUserController extends BaseController {
  private final UserService userService;

  @GetMapping("/")
  public ResponseEntity<Page<UserResponseDTO>> getAllUsers(
      @PageableDefault(size = 5) Pageable pageable,
      @ParameterObject UserSearchFilter userSearchFilter) {
    Page<UserResponseDTO> response =
        userService.getAllUsers(pageable, userSearchFilter.getPredicate());
    return ResponseEntity.ok(response);
  }
}
