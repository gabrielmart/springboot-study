package br.com.martins.todolist.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.martins.todolist.api.dtos.responses.ApiResponseDto;
import br.com.martins.todolist.api.dtos.responses.TokenResponseDto;
import br.com.martins.todolist.infra.security.domain.services.AuthenticationService;

@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthenticationController {

  private AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping()
  public ResponseEntity<ApiResponseDto<TokenResponseDto>> authenticate(Authentication authentication) {
    var tokenResponseDto = new TokenResponseDto(authenticationService.authenticate(authentication));

    var apiResponseDto = new ApiResponseDto<TokenResponseDto>(HttpStatus.CREATED, "Token gerado", tokenResponseDto,
        null);
        
    return ResponseEntity.status(apiResponseDto.getStatus()).body(apiResponseDto);
  }
}
