package com.carrentalbackend.users;


import com.carrentalbackend.authentification.Token;
import com.carrentalbackend.authentification.enums.Role;
import com.carrentalbackend.users.dto.UserDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private String number;
  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private Date createdAt;
  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  public User(Long id, String firstname, String lastname, String email, String password, String number, Role role, List<Token> tokens, Date createdAt, Date updatedAt) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.number = number;
    this.role = role;
    this.tokens = tokens;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public User() {
  }

  // getdto
    public UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setFirstname(firstname);
        userDto.setLastname(lastname);
        userDto.setEmail(email);
        userDto.setNumber(number);
        userDto.setCreatedAt(createdAt);
        userDto.setUpdatedAt(updatedAt);
        return userDto;
    }

  public static UserBuilder builder() {
    return new UserBuilder();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (role == null) {
      return List.of();
    }
    return role.getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .toList();
  }

  public Long getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return firstname + " " + lastname;
  }

  public Role getRole() {
    return role;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  public String getNumber() {
    return number;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public String toString() {
    return "User(id=" + this.getId() + ", firstname=" + this.getFirstname() + ", lastname=" + this.getLastname() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", role=" + this.getRole();
  }

  public List<Token> getTokens() {
    return this.tokens;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public void setTokens(List<Token> tokens) {
    this.tokens = tokens;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof User)) return false;
    final User other = (User) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$id = this.getId();
    final Object other$id = other.getId();
    if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
    final Object this$firstname = this.getFirstname();
    final Object other$firstname = other.getFirstname();
    if (this$firstname == null ? other$firstname != null : !this$firstname.equals(other$firstname)) return false;
    final Object this$lastname = this.getLastname();
    final Object other$lastname = other.getLastname();
    if (this$lastname == null ? other$lastname != null : !this$lastname.equals(other$lastname)) return false;
    final Object this$email = this.getEmail();
    final Object other$email = other.getEmail();
    if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
    final Object this$password = this.getPassword();
    final Object other$password = other.getPassword();
    if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
    final Object this$number = this.getNumber();
    final Object other$number = other.getNumber();
    if (this$number == null ? other$number != null : !this$number.equals(other$number)) return false;
    final Object this$role = this.getRole();
    final Object other$role = other.getRole();
    if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
    final Object this$tokens = this.getTokens();
    final Object other$tokens = other.getTokens();
    if (this$tokens == null ? other$tokens != null : !this$tokens.equals(other$tokens)) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof User;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $id = this.getId();
    result = result * PRIME + ($id == null ? 43 : $id.hashCode());
    final Object $firstname = this.getFirstname();
    result = result * PRIME + ($firstname == null ? 43 : $firstname.hashCode());
    final Object $lastname = this.getLastname();
    result = result * PRIME + ($lastname == null ? 43 : $lastname.hashCode());
    final Object $email = this.getEmail();
    result = result * PRIME + ($email == null ? 43 : $email.hashCode());
    final Object $password = this.getPassword();
    result = result * PRIME + ($password == null ? 43 : $password.hashCode());
    final Object $number = this.getNumber();
    result = result * PRIME + ($number == null ? 43 : $number.hashCode());
    final Object $role = this.getRole();
    result = result * PRIME + ($role == null ? 43 : $role.hashCode());
    final Object $tokens = this.getTokens();
    result = result * PRIME + ($tokens == null ? 43 : $tokens.hashCode());
    return result;
  }

  public static class UserBuilder {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String number;
    private Role role;
    private List<Token> tokens;
    private Date createdAt;
    private Date updatedAt;

    UserBuilder() {
    }

    public UserBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public UserBuilder firstname(String firstname) {
      this.firstname = firstname;
      return this;
    }

    public UserBuilder lastname(String lastname) {
      this.lastname = lastname;
      return this;
    }

    public UserBuilder email(String email) {
      this.email = email;
      return this;
    }

    public UserBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UserBuilder number(String number) {
      this.number = number;
      return this;
    }

    public UserBuilder role(Role role) {
      this.role = role;
      return this;
    }

    public UserBuilder tokens(List<Token> tokens) {
      this.tokens = tokens;
      return this;
    }

    public UserBuilder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public UserBuilder updatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public User build() {
      return new User(this.id, this.firstname, this.lastname, this.email, this.password, this.number, this.role, this.tokens, this.createdAt, this.updatedAt);
    }

    public String toString() {
      return "User.UserBuilder(id=" + this.id + ", firstname=" + this.firstname + ", lastname=" + this.lastname + ", email=" + this.email + ", password=" + this.password + ", number=" + this.number + ", role=" + this.role + ", tokens=" + this.tokens + ")";
    }
  }
}
