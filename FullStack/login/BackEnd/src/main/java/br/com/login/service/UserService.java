package br.com.login.service;

import br.com.login.infra.security.TokenService;
import br.com.login.model.User;
import br.com.login.repository.IUserRepository;
import br.com.login.service.request.RequestLogin;
import br.com.login.service.request.RequestRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<?> login(RequestLogin requestLogin) {
        var userInvalid = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username/password supplied!");
        if (checkIfParamsIsNotNull(requestLogin.email(), requestLogin.password())) return userInvalid;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestLogin.email(), requestLogin.password()));
            User user = (User) repository.findByEmail(requestLogin.email());
            if (user != null) {
                var token = tokenService.generateToken(user);
                return ResponseEntity.ok(token);
            } else {
                return userInvalid;
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> register(RequestRegister requestRegister) {
        if(repository.findByEmail(requestRegister.email()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is already an email address registered.");
        } else if (checkIfParamsIsNotNull(requestRegister.email(), requestRegister.password())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some parameters are null or empty.");
        }
        try {
            var password = new BCryptPasswordEncoder().encode(requestRegister.password());
            repository.save(new User(requestRegister.email(), password));
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            String errorMessage = "Failed to create user: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @Override
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    private boolean checkIfParamsIsNotNull(String email, String password) {
        return email == null || password == null || email.isBlank() || password.isBlank();
    }
}
