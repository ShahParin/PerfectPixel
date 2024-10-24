package model;

/**
 * A custom exception class when the image has no pixels defined.
 */
public class NothingThereException extends RuntimeException {

  /**
   * Constructs a new NothingThereException.
   *
   * @param message the message to be written.
   */
  public NothingThereException(String message) {
    super(message);
  }
}
