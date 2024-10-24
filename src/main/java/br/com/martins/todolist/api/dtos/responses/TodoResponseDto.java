package br.com.martins.todolist.api.dtos.responses;

public class TodoResponseDto {
  private final Long id;
  private final String title;
  private final String description;
  private final Boolean done;
  private final Integer priority;
  private final UserResponseDto user;

  public TodoResponseDto(Long id, String title, String description, Boolean done, Integer priority,
      UserResponseDto user) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.done = done;
    this.priority = priority;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getDone() {
    return done;
  }

  public Integer getPriority() {
    return priority;
  }

  public UserResponseDto getUser() {
    return user;
  }

}
