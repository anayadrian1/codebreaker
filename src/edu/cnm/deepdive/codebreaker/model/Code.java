package edu.cnm.deepdive.codebreaker.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Generates a secret code.
 */
public class Code {

  private final char[] secret;

  /**
   * Creates a random code from given string of characters.
   *
   * @param pool The characters allowed to be used in the code.
   * @param length The length of the code.
   * @param rng Generates a random string of letters from given string.
   */
  public Code(String pool, int length, Random rng) {
    secret = new char[length];
    for (int i = 0; i < length; i++) {
      secret[i] = pool.charAt(rng.nextInt(pool.length()));
    }
  }

  @Override
  public String toString() {
    return new String(secret);
  }

  /**
   * Uses the secret code constructed in the {@link Code} and compares the users guess to the
   * actual code. The program will respond with how many colors they got correct and how many were
   * close, but not in the right position.
   */
  public class Guess {

    private static final String STRING_FORMAT = "{text: \"%s\", correct: %d, close: %d}";
    private final String text;
    private final int correct;
    private final int close;

    /**
     * Tests how many of the characters in the guess were correct, and how many were close.
     *
     * @param text Takes the users guess text
     */
    public Guess(String text) {
      this.text = text;
      int correct = 0;
      int close = 0;

      Map<Character, Set<Integer>> letterMap = getLetterMap(text);

      char[] work = Arrays.copyOf(secret, secret.length);
      for (int i = 0; i < work.length; i++) {
        char letter = work[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
        if (positions.contains(i)) {
          correct++;
          positions.remove(i);
          work[i] = 0;
        }
      }

      for (char letter : work) {
        if (letter != 0) {
          Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
          if (positions.size() > 0) {
            close++;
            Iterator<Integer> iter = positions.iterator();
            iter.next();
            iter.remove();
          }
        }
      }

      this.correct = correct;
      this.close = close;
    }

    private Map<Character, Set<Integer>> getLetterMap(String text) {
      Map<Character, Set<Integer>> letterMap = new HashMap<>();
      char[] letters = text.toCharArray();
      for (int i = 0; i < letters.length; i++) {
        char letter = letters[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, new HashSet<>());
        positions.add(i);
        letterMap.putIfAbsent(letter, positions);
      }
      return letterMap;
    }

    @Override
    public String toString() {
      return String.format(STRING_FORMAT, text, correct, close);
    }

    /**
     * Returns text of this instance.
     */
    public String getText() {
      return text;
    }

    /**
     * Returns the number of correct colors in the secret code that also have the same position.
     */
    public int getCorrect() {
      return correct;
    }

    /**
     * Returns the number of colors that are correct, but not in the correct position.
     */
    public int getClose() {
      return close;
    }
  }


}
