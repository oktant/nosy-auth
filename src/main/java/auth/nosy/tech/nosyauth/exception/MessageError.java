package auth.nosy.tech.nosyauth.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MessageError {
  ACCESS_FORBIDDEN_EXCEPTION("Authorization server is not responding, please try again later");


  private String message;

  MessageError(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
