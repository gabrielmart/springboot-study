package br.com.martins.todolist.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.martins.todolist.api.dtos.requests.AuthenticationRequestDto;
import br.com.martins.todolist.api.dtos.responses.ApiResponseDto;
import br.com.martins.todolist.api.dtos.responses.TokenResponseDto;
import br.com.martins.todolist.infra.security.domain.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/authenticate")
@Tag(name = "Authentication", description = "Endpoints to manage token generation for authentication")
public class AuthenticationController {

  private AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Operation(summary = "Authenticate user and generate token", description = "Generates a JWT token for the authenticated user based on their credentials.", security = {
      @SecurityRequirement(name = "Basic Authentication") }, responses = {
          @ApiResponse(responseCode = "201", description = "Token generated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
          @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
      }, requestBody = @RequestBody(description = "User credentials for authentication", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationRequestDto.class))))
  @PostMapping
  public ResponseEntity<ApiResponseDto<TokenResponseDto>> authenticate(Authentication authentication) {
    var tokenResponseDto = new TokenResponseDto(authenticationService.authenticate(authentication));

    var apiResponseDto = new ApiResponseDto<TokenResponseDto>(HttpStatus.CREATED, "Token generated", tokenResponseDto,
        null);

    return ResponseEntity.status(apiResponseDto.getStatus()).body(apiResponseDto);
  }
}
