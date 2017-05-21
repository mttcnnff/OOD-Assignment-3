package cs3500.hw02.card;

/**
 * Type for the four types of suits in a deck of Cards. <br>
 * Hearts: ♥ <br>
 * Diamonds: ♦ <br>
 * Spades: ♠ <br>
 * Clubs: ♣ <br>
 */
public enum CardSuit {
  hearts("red"),
  diamonds("red"),
  spades("black"),
  clubs("black");

  private String color;

  CardSuit(String color) {
    this.color = color;
  }

  public boolean sameColorAs(CardSuit suit) {
    return this.color == suit.color;
  }

  @Override
  public String toString() {
    switch (this) {
      case hearts:
        return "♥";
      case diamonds:
        return "♦";
      case spades:
        return "♠";
      case clubs:
        return "♣";
      default:
        throw new IllegalArgumentException("card suit does not exist.");
    }
  }
}
