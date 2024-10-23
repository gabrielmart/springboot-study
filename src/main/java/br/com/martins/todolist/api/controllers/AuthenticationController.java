package br.com.martins.todolist.api.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.martins.todolist.infra.security.domain.services.AuthenticationService;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

  private AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping()
  public String authenticate(Authentication authentication) {
    return authenticationService.authenticate(authentication);
  }
}
