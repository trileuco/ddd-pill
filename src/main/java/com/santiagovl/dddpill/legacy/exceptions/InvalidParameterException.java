package com.santiagovl.dddpill.legacy.exceptions;

public class InvalidParameterException extends RuntimeException {

  public InvalidParameterException(final String message) {
    super(message);
  }
}
