package com.labs.springswagger.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
  private Long id;

  @NotNull(message = "NotNull.UserDTO.name")
  @Size(min = 1, max = 64, message = "Size.UserDTO.name")
  private String name;

  private Integer salary;

  public UserDTO(Long id, String name, Integer salary) {
    this.id = id;
    this.name = name;
    this.salary = salary;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }
}
