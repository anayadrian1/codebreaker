package edu.cnm.deepdive.codebreaker.model;

/**
 * Exception class to handle the guess has invalid characters.
 */
public class IllegalGuessCharacterException extends IllegalArgumentException {

  /**
   * Overloaded methods to handle the invalid characters of a guess and returns the message of the
   * IllegalArgumentException and/or the cause.
   */
  public IllegalGuessCharacterException() {
  }

  public IllegalGuessCharacterException(String message) {
    super(message);
  }

  public IllegalGuessCharacterException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalGuessCharacterException(Throwable cause) {
    super(cause);
  }
}
