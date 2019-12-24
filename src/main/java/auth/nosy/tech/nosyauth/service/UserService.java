package auth.nosy.tech.nosyauth.service;

import auth.nosy.tech.nosyauth.exception.PasswordIsNotValidException;
import auth.nosy.tech.nosyauth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {
  private KeycloakService keycloakService;

  @Autowired
  public UserService(KeycloakService keycloakClient) {
    this.keycloakService = keycloakClient;
  }

  public User getUserInfo(HttpServletRequest request) {

    return keycloakService.getUserInfo(request.getUserPrincipal().getName());
  }

  public void deleteUser(HttpServletRequest request) {


    String obtainedUser = request.getUserPrincipal().getName();

    keycloakService.deleteUsername(obtainedUser);
  }

  public void logoutUser(HttpServletRequest request) {

    keycloakService.logoutUser(request.getUserPrincipal().getName());
  }

  public User addUser(User user) {
    if (!isValidPassword(user.getPassword())) {
      throw new PasswordIsNotValidException();
    }
    keycloakService.registerNewUser(user);
    return user;
  }

  private boolean isValidPassword(String password) {
    return null != password && password.length() > 5;
  }
}
