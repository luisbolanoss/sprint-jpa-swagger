package com.labs.springswagger.exception;

public class EntityNoFound extends RuntimeException {
  private String className;
  private String id;

  public EntityNoFound(String className, String id) {
    this.className = className;
    this.id = id;
  }

  @Override
  public String getMessage() {
    return String.format("the %s no found  in %s", id, className);
  }
}
