package br.com.martins.todolist.infra.security.domain.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private JwtService jwtService;

  public AuthenticationService(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  public String authenticate(Authentication authentication) {
    return jwtService.generateToken(authentication);
  }
}
