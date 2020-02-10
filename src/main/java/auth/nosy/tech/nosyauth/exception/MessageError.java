package auth.nosy.tech.nosyauth.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MessageError {
  ACCESS_FORBIDDEN_EXCEPTION("Authorization server is not responding, please try again later"),
  REFRESH_TOKEN_EXCEPTION("Refresh token is not valid"),
  INVALID_USERNAME_PASSWORD("Username or password is incorrect"),
  USER_ALREADY_EXISTS_EXCEPTION("User already exists. Please try another email");



  private String message;

  MessageError(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
