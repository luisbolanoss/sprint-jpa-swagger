package com.labs.springswagger.controller;

import com.labs.springswagger.DTO.MessageDTO;
import com.labs.springswagger.DTO.UserDTO;
import com.labs.springswagger.exception.EntityNoFound;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api("/api/tasks")
public interface UserController {
  @ApiOperation(value = "Find all users", notes = "Retrieving the collection of user", response = UserDTO[].class)
  @ApiResponses({
      @ApiResponse(code = 200, message = "Success", response = UserDTO[].class)
  })
  List<UserDTO> findAll();

  @ApiOperation(value = "Create User", notes = "Creating a new user", response = UserDTO.class)
  @ApiResponses({
      @ApiResponse(code = 200, message = "Success", response = UserDTO.class),
      @ApiResponse(code = 400, message = "Bad request", response = MessageDTO.class)
  })
  UserDTO create(@Valid @RequestBody UserDTO dto);

  @ApiOperation(value = "Update user", notes = "Updating an existing user", response = UserDTO.class)
  @ApiResponses({
      @ApiResponse(code = 200, message = "Success", response = UserDTO.class),
      @ApiResponse(code = 400, message = "Bad request", response = MessageDTO.class),
      @ApiResponse(code = 404, message = "Not found", response = MessageDTO.class)
  })
  UserDTO update(@PathVariable Long id, @Valid @RequestBody UserDTO dto);

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete user", notes = "Deleting an existing user")
  @ApiResponses({
      @ApiResponse(code = 204, message = "Success"),
      @ApiResponse(code = 404, message = "Not found", response = MessageDTO.class)
  })
  void delete(@PathVariable Long id);

  MessageDTO handleValidationException(MethodArgumentNotValidException ex);

  MessageDTO handleNotFoundException(EntityNoFound ex);
}
