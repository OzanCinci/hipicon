package com.ozan.be.user;

import com.ozan.be.customException.types.DataNotFoundException;
import com.ozan.be.user.domain.User;
import com.ozan.be.user.dtos.UserResponseDTO;
import com.ozan.be.utils.ModelMapperUtils;
import com.ozan.be.utils.PageableUtils;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository repository;

  public Page<UserResponseDTO> getAllUsers(Pageable pageable, Predicate filter) {
    Pageable finalPageable = PageableUtils.prepareDefaultSorting(pageable);
    Page<User> userPage = repository.findAll(filter, finalPageable);

    List<UserResponseDTO> userResponseDTOList =
        ModelMapperUtils.mapAll(userPage.getContent(), UserResponseDTO.class);

    return new PageImpl<>(userResponseDTOList, userPage.getPageable(), userPage.getTotalElements());
  }

  public User getUserById(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new DataNotFoundException("No user found with id: " + id));
  }

  public UserResponseDTO getUserDetails(UUID id) {
    User user = getUserById(id);
    return ModelMapperUtils.map(user, UserResponseDTO.class);
  }
}
