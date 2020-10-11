package edu.cnm.deepdive.codebreaker.model;

/**
 * Exception class to handle the guess when it is of invalid length.
 */
public class IllegalGuessLengthException extends IllegalArgumentException {

  /**
   * Overloaded methods to handle the invalid guess length and returns the message of the
   * IllegalArgumentException and/or the cause.
   */
  public IllegalGuessLengthException() {
  }

  public IllegalGuessLengthException(String message) {
    super(message);
  }

  public IllegalGuessLengthException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalGuessLengthException(Throwable cause) {
    super(cause);
  }
}
