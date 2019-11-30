package com.labs.springswagger.service;

import com.labs.springswagger.DTO.UserDTO;
import com.labs.springswagger.entity.User;
import java.util.List;

public interface UserService {
  List<UserDTO> findAll();

  UserDTO create(UserDTO userDTO);

  UserDTO update(Long id, UserDTO userDTO);

  void delete(Long id);

  User findByIdSafe(Long id);
}
