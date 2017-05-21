package cs3500.hw02.pile;

import java.util.List;

import cs3500.hw02.PileType;
import cs3500.hw02.card.Card;

/**
 * Created by Matt on 5/16/17.
 */
public class CascadePile extends AbstractPile {

  /**
   * Constructor for Cascade Pile.
   * @param numOfPiles number of piles to be bade in this.
   * @param deck deck of card to be dealt.
   */
  public CascadePile(int numOfPiles, List<Card> deck) {

    super(PileType.CASCADE, numOfPiles);

    //Deal cards to cascade pile
    int dealCount = 0;
    for (Card c : deck) {
      this.pileContents.get(dealCount).add(c);
      dealCount = (dealCount + 1) % numOfPiles;
    }
  }

  /**
   * Tries to put card passed in as param at the end of specified pile.
   * Checks if move is legal for Cascade pile.
   * If move is legal, the card is moved and True is returned.
   * If the move is illegal, the card is not moved and False is returned.
   * Cascade pile Moves Rules:
   * "However, a card from some pile can be moved to the end of a cascade pile if and only if its
   * color is different from that of the currently last card, and its value is exactly one less
   * than that of the currently last card".
   * @return true if move is legal and made, false if it was not.
   */
  @Override
  public boolean put(int pileNumber, Card card) {
    Card topCard = this.pileContents.get(pileNumber).get(this.pileContents.get(pileNumber).size()
            - 1);
    if (!topCard.sameColorAs(card) && topCard.compare(card) == 1) {
      this.pileContents.get(pileNumber).add(card);
      return true;
    } else {
      return false;
    }
  }
}
