package br.com.login.controller;

import br.com.login.service.IUserService;
import br.com.login.service.request.RequestLogin;
import br.com.login.service.request.RequestRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private IUserService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestLogin requestLogin) {
        return service.login(requestLogin);
    }

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody RequestRegister requestRegister) {
        return service.register(requestRegister);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return service.getUsers();
    }
}
