package br.com.martins.todolist.exceptions;

public class SubjectNotInformedException extends RuntimeException {
  public SubjectNotInformedException(String message) {
    super(message);
  }
}
