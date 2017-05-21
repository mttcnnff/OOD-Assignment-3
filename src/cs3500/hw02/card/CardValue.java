package cs3500.hw02.card;

/**
 * Type for the thirteen values of cards in a deck of Cards. <br>
 * <i>(Aces are considered low).</i>
 * Ace: 1 <i>(low)</i> <br>
 * Two: 2 <br>
 * Three: 3 <br>
 * Four: 4 <br>
 * Five: 5 <br>
 * Six: 6 <br>
 * Seven: 7 <br>
 * Eight: 8 <br>
 * Nine: 9 <br>
 * Ten: 10 <br>
 * Jack: 11 <br>
 * Queen: 12 <br>
 * King: 13 <br>
 */
public enum CardValue {

  ace(1),
  two(2),
  three(3),
  four(4),
  five(5),
  six(6),
  seven(7),
  eight(8),
  nine(9),
  ten(10),
  jack(11),
  queen(12),
  king(13);


  private int value;

  CardValue(int value) {
    this.value = value;
  }

  public int getCardValue() {
    return this.value;
  }

  @Override
  public String toString() {
    if (this.value > 1 && this.value < 11) {
      return Integer.toString(this.value);
    } else {
      switch (this) {
        case ace:
          return "A";
        case jack:
          return "J";
        case queen:
          return "Q";
        case king:
          return "K";
        default:
          throw new IllegalArgumentException("card value does not exist.");
      }
    }
  }


}
