package auth.nosy.tech.nosyauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UserDto {
  @NotEmpty
  @NotNull
  @Email
  private String email;

  @NotEmpty
  @NotNull
  private String password;

  @NotEmpty
  @NotNull
  private String firstName;
  private String lastName;


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
