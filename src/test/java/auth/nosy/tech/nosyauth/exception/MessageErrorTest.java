package auth.nosy.tech.nosyauth.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageErrorTest {

    @Test
    void getMessageTest() {
        assertEquals("Authorization server is not responding, please try again later", MessageError.ACCESS_FORBIDDEN_EXCEPTION.getMessage());
    }
}