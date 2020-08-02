package auth.nosy.tech.nosyauth.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionControllerTest {
    @InjectMocks
    ExceptionController exceptionController;

    @Test
    public void authorizationServerCannotPerformTheOperationTest() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                exceptionController.authorizationServerCannotPerformTheOperation().getStatusCode());
    }

    @Test
    public void refreshTokenExceptionTest(){
        assertEquals(HttpStatus.BAD_REQUEST,
                exceptionController.refreshTokenException().getStatusCode());
    }

    @Test
    public void invalidUsernameOrPasswordTest(){
        assertEquals(HttpStatus.UNAUTHORIZED,
                exceptionController.invalidUsernameOrPassword().getStatusCode());
    }
}