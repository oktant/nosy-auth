package auth.nosy.tech.nosyauth.controller;

import auth.nosy.tech.nosyauth.dto.TokenCollectionDto;
import auth.nosy.tech.nosyauth.dto.UserDto;
import auth.nosy.tech.nosyauth.mapper.TokenCollectionMapper;
import auth.nosy.tech.nosyauth.mapper.UserMapper;
import auth.nosy.tech.nosyauth.service.KeycloakService;
import auth.nosy.tech.nosyauth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthController {
    private UserService userService;
    private KeycloakService keycloakService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(
            UserService userService,  KeycloakService keycloakService) {
        this.userService = userService;
        this.keycloakService = keycloakService;
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        userService.logoutUser(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/status")
    public ResponseEntity<Boolean> isAuthenticated(@RequestBody TokenCollectionDto token)  {
        return new ResponseEntity<>(keycloakService.isAuthenticated(token.getAccessToken()), HttpStatus.OK);
    }

    @PostMapping(value = "/token")
    public ResponseEntity<TokenCollectionDto> getToken(@RequestBody @Valid UserDto userdto)
            throws IOException {
        logger.info("Getting token");
        return new ResponseEntity<>(
                TokenCollectionMapper.INSTANCE.
                        toTokenCollectionDto(keycloakService.getTokens(UserMapper.INSTANCE.toUser(userdto))), HttpStatus.OK);
    }

    @PostMapping(value="refresh-token")
    public ResponseEntity<TokenCollectionDto> getRefreshToken(@RequestBody @Valid TokenCollectionDto collectionDto) throws IOException {
        return new ResponseEntity<>(
                TokenCollectionMapper.INSTANCE.
                        toTokenCollectionDto(keycloakService.refreshToken(collectionDto.getRefreshToken())), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserDto> newUser(@RequestBody @Valid UserDto userdto) {
        return new ResponseEntity<>(
                UserMapper.INSTANCE.toUserDto(userService.addUser(UserMapper.INSTANCE.toUser(userdto))), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users")
    public ResponseEntity<String> deleteUsername(HttpServletRequest request) {
        userService.deleteUser(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<UserDto> getUserProfile(HttpServletRequest request) {
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserDto(userService.getUserInfo(request)), HttpStatus.OK);
    }
}
