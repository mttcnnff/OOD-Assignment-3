package cs3500.hw02.card;

/**
 * Created by Matt on 5/14/17.
 */
public class Card {
  private CardSuit suit;
  private CardValue value;

  public Card(CardValue value, CardSuit suit) {
    this.value = value;
    this.suit = suit;
  }

  public String toString() {
    return this.value.toString() + this.suit.toString();
  }

  public boolean isOfSuit(CardSuit suit) {
    return this.suit == suit;
  }

  public boolean isOfValue(CardValue value) {
    return this.value == value;
  }

  public boolean sameSuitAs(Card card) {
    return this.suit == card.suit;
  }

  public boolean sameColorAs(Card card) {
    return this.suit.sameColorAs(card.suit);
  }

  public int compare(Card card) {
    return this.value.getCardValue() - card.value.getCardValue();
  }
}
