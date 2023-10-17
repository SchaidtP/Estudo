package br.com.login.repository;

import br.com.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    UserDetails findByEmail(String email);
}
