package br.com.springsecurity2.controllers;

import br.com.springsecurity2.domain.user.AuthenticationDTO;
import br.com.springsecurity2.domain.user.LoginResponseDTO;
import br.com.springsecurity2.domain.user.RegisterDTO;
import br.com.springsecurity2.domain.user.User;
import br.com.springsecurity2.infra.security.TokenService;
import br.com.springsecurity2.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping()
    public void login(){
        System.out.println("ADMIN AQUI");
    }
}
