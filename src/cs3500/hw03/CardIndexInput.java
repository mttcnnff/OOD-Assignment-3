package cs3500.hw03;

/**
 * Created by Matt on 5/23/17.
 */
public class CardIndexInput extends AbstractInput {

  public CardIndexInput() {
    super();
  }

  @Override
  public boolean isValid() {
    String pattern ="\\d+";
    if (this.move.matches(pattern)) {
      return true;
    } else {
      return false;
    }
  }

  public Integer getCardIndex() {
    if (this.isValid()) {
      return Integer.valueOf(this.move);
    } else {
      return 0;
    }
  }
}
