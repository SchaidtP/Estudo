package br.com.springsecurity2.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
