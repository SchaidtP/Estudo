package br.com.springsecurity.service.auth;

import br.com.springsecurity.model.User;
import br.com.springsecurity.repository.UserRepository;
import br.com.springsecurity.security.jwt.JwtTokenProvider;
import br.com.springsecurity.service.auth.request.RequestAccountCredentials;
import br.com.springsecurity.service.auth.response.ResponseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    //public ResponseEntity<ResponseToken> signin(RequestAccountCredentials data) {
    public ResponseEntity<?> signin(RequestAccountCredentials data) {
        try {
            String userName = data.getUserName();
            String password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            User user = userRepository.findByUserName(userName);
            if (user != null) {
                ResponseToken tokenResponse = tokenProvider.createAccessToken(userName, user.getRoles());
                return ResponseEntity.ok(tokenResponse);
            } else {
                throw new UsernameNotFoundException("Username " + userName + " not found!");
            }
        } /*catch (Exception e) { throw new Exception("Invalid username/password supplied!"); }*/
        catch (AuthenticationException e) {
            String errorMessage = "Invalid username/password supplied!";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }

    public ResponseEntity<ResponseToken> refreshToken(String userName, String refreshToken) {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            ResponseToken tokenResponse = tokenProvider.refreshToken(refreshToken);
            return ResponseEntity.ok(tokenResponse);
        } else {
            throw new UsernameNotFoundException("Username " + userName + " not found!");
        }
    }
}
