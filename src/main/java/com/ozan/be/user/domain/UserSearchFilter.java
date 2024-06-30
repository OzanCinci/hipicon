package com.ozan.be.user.domain;

import static java.util.Objects.nonNull;

import com.ozan.be.common.AbstractSearchFilter;
import com.querydsl.core.types.Predicate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchFilter extends AbstractSearchFilter {
  List<Role> roles;
  String email;
  String q;

  @Override
  public void buildPredicateList(List<Predicate> predicateList) {
    if (nonNull(roles) && !roles.isEmpty()) {
      predicateList.add(com.ozan.be.user.domain.QUser.user.role.in(roles));
    }

    if (nonNull(email)) {
      predicateList.add(com.ozan.be.user.domain.QUser.user.email.containsIgnoreCase(email));
    }

    if (nonNull(q)) {
      predicateList.add(
          com.ozan.be.user.domain.QUser.user
              .firstName
              .containsIgnoreCase(q)
              .or(com.ozan.be.user.domain.QUser.user.lastName.containsIgnoreCase(q)));
    }
  }
}
