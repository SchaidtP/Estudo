package br.com.login.service;

import br.com.login.service.request.RequestLogin;
import br.com.login.service.request.RequestRegister;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> login(RequestLogin requestLogin);

    ResponseEntity<?> register(RequestRegister requestRegister);

    ResponseEntity<?> getUsers();
}
