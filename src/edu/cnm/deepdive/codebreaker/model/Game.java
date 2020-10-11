package edu.cnm.deepdive.codebreaker.model;

import edu.cnm.deepdive.codebreaker.model.Code.Guess;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Creates a game of codebreaker by generating a string of random letters of a set length using
 * {@link Game#Game(String, int, Random)}. The player can guess the secret code and the program
 * will check to see if the guess is valid.
 */
public class Game {

  private static final String BAD_GUESS_PATTERN_FORMAT = "^.*[^%s].*$";
  private static final String ILLEGAL_LENGTH_MESSAGE =
      "Invalid guess length: required=%d: provided=%d";
  private static final String ILLEGAL_CHARACTER_MESSAGE =
      "Guess includes invalid characters: required=%s; provided=%s";
  private final Code code;
  private final List<Guess> guesses;
  private final String pool;
  private final int length;
  private final String badGuessPattern;

  /**
   * Creates a game of codebreaker, generating a random code.
   *
   * @param pool Characters to be used in the code.
   * @param length Length of the code.
   * @param rng Generates random string from given pool.
   */
  public Game(String pool, int length, Random rng) {
    code = new Code(pool, length, rng);
    guesses = new LinkedList<>();
    this.pool = pool;
    this.length = length;
    badGuessPattern = String.format(BAD_GUESS_PATTERN_FORMAT, pool);
  }

  /**
   * Returns randomly generated secret code.
   */
  public Code getCode() {
    return code;
  }

  /**
   * Returns the list of guesses from the player.
   */
  public List<Guess> getGuesses() {
    return Collections.unmodifiableList(guesses);
  }

  /**
   * Returns the pool of letters that can be used in the code.
   */
  public String getPool() {
    return pool;
  }

  /**
   * Returns the length of the code.
   */
  public int getLength() {
    return length;
  }

  /**
   * Returns the number of guesses that have been made.
   */
  public int getGuessCount() {
    return guesses.size();
  }

  /**
   * Tests the users guess against certain conditions to see if the guess is a valid guess and
   * denies any bad guess.
   *
   * @param text The guess from the player
   * @return The guess from the player if it is a valid guess.
   * @throws IllegalGuessLengthException If the guess length is invalid.
   * @throws IllegalGuessCharacterException If the characters used to guess are invalid.
   */
  public Guess guess(String text)
      throws IllegalGuessLengthException, IllegalGuessCharacterException {
    if (text.length() != length) {
      throw new IllegalGuessLengthException(
          String.format(ILLEGAL_LENGTH_MESSAGE, length, text.length()));
    }
    if (text.matches(badGuessPattern)) {
      throw new IllegalGuessCharacterException(String.format(ILLEGAL_CHARACTER_MESSAGE, pool, text));
    }
    Guess guess = code.new Guess(text);
    guesses.add(guess);
    return guess;
  }

  /**
   * Restarts the game of codebreaker.
   */
  public void restart() {
    guesses.clear();
  }
}
