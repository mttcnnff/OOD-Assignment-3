package cs3500.hw03.input;

/**
 * Created by Matt on 5/23/17.
 */
public abstract class AbstractInput {

  protected String move;

  public AbstractInput() {
    this.move = "";
  }

  public abstract boolean isValid();

  public boolean quitCheck() {
    if (move.equals("q") || move.equals("Q")) {
      return true;
    }
    return false;
  }

  public void read(String input) {
    this.move = input;
  }

  public String toString() {
    return this.move;
  }
}
