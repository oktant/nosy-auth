package auth.nosy.tech.nosyauth.controller;

import auth.nosy.tech.nosyauth.dto.LoginUserDto;
import auth.nosy.tech.nosyauth.dto.TokenCollectionDto;
import auth.nosy.tech.nosyauth.dto.TokenDto;
import auth.nosy.tech.nosyauth.dto.UserDto;
import auth.nosy.tech.nosyauth.mapper.UserMapper;
import auth.nosy.tech.nosyauth.model.User;
import auth.nosy.tech.nosyauth.service.KeycloakService;
import auth.nosy.tech.nosyauth.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    @Mock
    KeycloakService keycloakService;

    UserDto userDto=new UserDto();

    LoginUserDto loginUserDto=new LoginUserDto();

    @Before
    public void setUp(){
        userDto.setFirstName("testFirstName");
        userDto.setLastName("testLastName");
        userDto.setPassword("testPassword");
        userDto.setEmail("test@nosy.tech");
        loginUserDto.setEmail("test@nosy.tech");
        loginUserDto.setPassword("testPassword");
    }

    @Test
    public void logoutTest() {
        HttpServletRequest httpServletRequest= Mockito.mock(HttpServletRequest.class);
        doNothing().when(userService).logoutUser(httpServletRequest);
        assertEquals(HttpStatus.NO_CONTENT, authController.logout(httpServletRequest).getStatusCode());
    }

    @Test
    public void isAuthenticatedTest()  {
        TokenCollectionDto tokenCollectionDto=new TokenCollectionDto();
        tokenCollectionDto.setAccessToken("testToken");
        TokenDto tokenDto=new TokenDto();
        tokenDto.setToken("testToken");
        doReturn(true).when(keycloakService).isAuthenticated(tokenDto.getToken());
        assertEquals(HttpStatus.OK, authController.isAuthenticated(tokenDto).getStatusCode());
    }

    @Test
    public void getTokenTest() throws IOException {
        assertEquals(HttpStatus.OK, authController.getToken(loginUserDto).getStatusCode());
    }

    @Test
    public void newUserTest() {
        assertEquals(HttpStatus.CREATED, authController.newUser(userDto).getStatusCode());
    }

    @Test
    public void deleteUsernameTest() {
        HttpServletRequest httpServletRequest= Mockito.mock(HttpServletRequest.class);
        doNothing().when(userService).deleteUser(httpServletRequest);
        assertEquals(HttpStatus.NO_CONTENT, authController.deleteUsername(httpServletRequest).getStatusCode());
    }

    @Test
    public void getUserProfileTest() {
        User user= UserMapper.INSTANCE.toUser(userDto);
        HttpServletRequest httpServletRequest= Mockito.mock(HttpServletRequest.class);
        doReturn(user).when(userService).getUserInfo(httpServletRequest);
        assertEquals(HttpStatus.OK, authController.getUserProfile(httpServletRequest).getStatusCode());
    }

}