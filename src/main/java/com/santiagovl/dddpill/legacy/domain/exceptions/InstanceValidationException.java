package com.santiagovl.dddpill.legacy.domain.exceptions;

public class InstanceValidationException extends RuntimeException {

  public InstanceValidationException(final String message) {
    super(message);
  }
}