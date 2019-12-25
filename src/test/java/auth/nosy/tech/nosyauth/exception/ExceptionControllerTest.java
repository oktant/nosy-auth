package auth.nosy.tech.nosyauth.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionControllerTest {
    @InjectMocks
    ExceptionController exceptionController;

    @Test
    public void authorizationServerCannotPerformTheOperation() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                exceptionController.authorizationServerCannotPerformTheOperation().getStatusCode());
    }

}