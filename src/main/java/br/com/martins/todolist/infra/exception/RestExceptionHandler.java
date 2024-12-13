package br.com.martins.todolist.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.martins.todolist.api.dtos.responses.ApiResponseDto;
import br.com.martins.todolist.exceptions.TodoNotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)))
  public ResponseEntity<ApiResponseDto<Void>> handleValidationException(MethodArgumentNotValidException ex) {
    // Your logic here...
    ApiResponseDto<Void> apiResponse = new ApiResponseDto<>(HttpStatus.BAD_REQUEST,
        "Validation failed", null, null);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @ExceptionHandler(TodoNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ApiResponse(responseCode = "404", description = "Todo not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)))
  public ResponseEntity<ApiResponseDto<Void>> todoNotFoundExceptionHandler(TodoNotFoundException ex) {
    ApiResponseDto<Void> apiResponse = new ApiResponseDto<>(HttpStatus.NOT_FOUND, ex.getMessage(), null, null);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)))
  public ResponseEntity<ApiResponseDto<Void>> handleGenericException(Exception ex) {
    ApiResponseDto<Void> apiResponse = new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR,
        "Internal server error", null, null);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }
}
