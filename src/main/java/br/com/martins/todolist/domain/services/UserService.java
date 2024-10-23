package br.com.martins.todolist.domain.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.martins.todolist.domain.entities.User;
import br.com.martins.todolist.domain.repositories.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User save(User user) {
    Boolean isUserExisting = userRepository.existsByUsername(user.getUsername());

    if (isUserExisting) {
      throw new UsernameNotFoundException("O username informado já está sendo utilizado");
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    User userSaved = userRepository.save(user);
    return userSaved;
  }

}
