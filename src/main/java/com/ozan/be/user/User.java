package com.ozan.be.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ozan.be.order.Order;
import com.ozan.be.review.PendingReviews;
import com.ozan.be.review.Review;
import com.ozan.be.token.Token;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  @JsonIgnore
  private String password;
  private String phone;
  @Enumerated(EnumType.STRING)
  private Role role;

  @JsonIgnore
  @JsonManagedReference
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "user")
  private List<Token> tokens;

  @JsonManagedReference
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "user")
  private List<Review> reviews = new ArrayList<>();

  @JsonManagedReference
  @JsonIgnore
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "user")
  private List<PendingReviews> pendingReviews = new ArrayList<>();

  @JsonManagedReference
  @JsonIgnore
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "user")
  private List<Order> orders = new ArrayList<>();


  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  @JsonIgnore
  public String getPassword() {
    return password;
  }

  @Override
  @JsonIgnore
  public String getUsername() {
    return email;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }
}
