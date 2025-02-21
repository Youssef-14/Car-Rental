package com.carrentalbackend.authentification.enums;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.carrentalbackend.authentification.enums.Permission.*;


@Getter
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  AGENT_READ,
                  AGENT_UPDATE,
                  AGENT_DELETE,
                  AGENT_CREATE
          )
  ),
  AGENT(
          Set.of(
                  AGENT_READ,
                  AGENT_UPDATE,
                  AGENT_DELETE,
                  AGENT_CREATE
          )
  ),
  CUSTOMER(
          Set.of(
                  CUSTOMER_READ,
                  CUSTOMER_UPDATE,
                  CUSTOMER_DELETE,
                  CUSTOMER_CREATE
          )
  );

  private final Set<Permission> permissions;

  private Role(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  // Ensure that permissions are never null
  public Set<Permission> getPermissions() {
    return permissions != null ? permissions : Collections.emptySet();
  }

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
