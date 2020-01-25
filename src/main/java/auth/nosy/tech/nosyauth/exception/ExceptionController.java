package auth.nosy.tech.nosyauth.exception;

import org.apache.http.conn.HttpHostConnectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = HttpHostConnectException.class)
    public ResponseEntity<MessageError> authorizationServerCannotPerformTheOperation() {
        return new ResponseEntity<>(MessageError.ACCESS_FORBIDDEN_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = RefreshTokenException.class)
    public ResponseEntity<MessageError> refreshTokenException() {
        return new ResponseEntity<>(MessageError.REFRESH_TOKEN_EXCEPTION, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidUsernameAndPasswordException.class)
    public ResponseEntity<MessageError> invalidUsernameOrPassword() {
        return new ResponseEntity<>(MessageError.INVALID_USERNAME_PASSWORD, HttpStatus.UNAUTHORIZED);
    }
}
