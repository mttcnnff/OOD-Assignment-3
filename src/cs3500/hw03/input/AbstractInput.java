package cs3500.hw03.input;

/**
 * Abstract class used to process inputs more easily.
 */
public abstract class AbstractInput {

  protected String move;

  /**
   * Constructor which initializes move as an empty string.
   */
  public AbstractInput() {
    this.move = "";
  }

  /**
   * Abstract method which determines if this input is valid.
   * @return true if valid, false if not.
   */
  public abstract boolean isValid();

  /**
   * Checks if input indicates that user wants to quit.
   * @return true if quit is detected, false if not.
   */
  public boolean quitCheck() {
    return move.equals("q") || move.equals("Q");
  }

  /**
   * Sets this input as given string.
   * @param input given input from scanner.
   */
  public void read(String input) {
    this.move = input;
  }

  /**
   * Returns this Input represented as a string.
   * @return string representation of this input.
   */
  public String toString() {
    return this.move;
  }
}
