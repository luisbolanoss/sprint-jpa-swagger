package com.labs.springswagger.controller.impl;


import com.labs.springswagger.DTO.MessageDTO;
import com.labs.springswagger.DTO.UserDTO;
import com.labs.springswagger.controller.UserController;
import com.labs.springswagger.exception.EntityNoFound;
import com.labs.springswagger.service.UserService;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UserDTO> findAll() {
    return userService.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO create(@Valid @RequestBody UserDTO dto) {
    return userService.create(dto);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDTO update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
    return userService.update(id, dto);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    userService.delete(id);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public MessageDTO handleValidationException(MethodArgumentNotValidException ex) {
    Locale locale = LocaleContextHolder.getLocale();
    String code = ex.getBindingResult().getFieldError().getDefaultMessage();
    return new MessageDTO(messageSource.getMessage(code, null, locale));
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(EntityNoFound.class)
  public MessageDTO handleNotFoundException(EntityNoFound ex) {
    String[] parameters = { ex.getMessage() };
    Locale locale = LocaleContextHolder.getLocale();
    return new MessageDTO(messageSource.getMessage("exception.EntityNotFound.description", parameters, locale));
  }
}
