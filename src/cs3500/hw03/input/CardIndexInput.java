package cs3500.hw03.input;

/**
 * Created by Matt on 5/23/17.
 */
public class CardIndexInput extends AbstractInput {

  public CardIndexInput() {
    super();
  }

  @Override
  public boolean isValid() {
    String pattern = "\\d+";
    return this.move.matches(pattern);
  }

  /**
   * Gets integer value of cardIndex input if input is valid.
   * @return integer representation of this move.
   */
  public Integer getCardIndex() {
    if (this.isValid()) {
      return Integer.valueOf(this.move);
    } else {
      return 0;
    }
  }
}
