package com.labs.springswagger.service.impl;

import com.labs.springswagger.DTO.UserDTO;
import com.labs.springswagger.entity.User;
import com.labs.springswagger.exception.EntityNoFound;
import com.labs.springswagger.repository.UserRepository;
import com.labs.springswagger.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  public List<UserDTO> findAll() {
    return userRepository.findAll().stream()
        .map(e -> new UserDTO(e.getId(), e.getName(), e.getSalary()))
        .collect(Collectors.toList());
  }

  @Transactional
  public UserDTO create(UserDTO userDTO) {
    User newUser = new User(userDTO.getId(), userDTO.getName(), userDTO.getSalary());
    newUser = userRepository.saveAndFlush(newUser);

    return new UserDTO(newUser.getId(), userDTO.getName(), userDTO.getSalary());
  }


  @Transactional
  public UserDTO update(Long id, UserDTO userDTO) {
    User findedUser = this.findByIdSafe(id);
    findedUser.setName(userDTO.getName());
    findedUser.setSalary(userDTO.getSalary());
    userRepository.saveAndFlush(findedUser);

    return new UserDTO(findedUser.getId(), findedUser.getName(), findedUser.getSalary());
  }

  @Transactional
  public void delete(Long id) {
    User user = this.findByIdSafe(id);
    userRepository.delete(user);
  }

  public User findByIdSafe(Long id) {
    Optional<User> userOptional = userRepository.findById(id);

    if (!userOptional.isPresent()) {
       throw new EntityNoFound(this.getClass().getName(), id.toString());
    }

    return userOptional.get();
  }
}
