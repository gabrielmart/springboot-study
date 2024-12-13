package br.com.martins.todolist.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

  private SecurityScheme createBearerAuthScheme() {
    return new SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .bearerFormat("JWT")
        .scheme("bearer");
  }

  private SecurityScheme createBasicAuthScheme() {
    return new SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .scheme("basic");
  }

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
        .addSecurityItem(new SecurityRequirement().addList("Basic Authentication"))
        .components(new Components()
            .addSecuritySchemes("Bearer Authentication", createBearerAuthScheme())
            .addSecuritySchemes("Basic Authentication", createBasicAuthScheme()))
        .info(new Info()
            .title("TodoList REST API")
            .description("TodoList Management Rest API")
            .version("0.1")
            .contact(new Contact().name("Gabriel Martins")
                .email("gabrielmartinsdesouzaa@hotmail.com").url("gabrielmartinsdesouzaa@hotmail.com"))
            .license(new License().name("MIT License")
                .url("https://opensource.org/licenses/MIT")));
  }
}
