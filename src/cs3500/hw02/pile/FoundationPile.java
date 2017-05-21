package cs3500.hw02.pile;

import cs3500.hw02.PileType;
import cs3500.hw02.card.Card;
import cs3500.hw02.card.CardValue;

/**
 * Created by Matt on 5/16/17.
 */
public class FoundationPile extends AbstractPile {

  public FoundationPile() {
    super(PileType.FOUNDATION, 4);
  }

  /**
   * Tries to put card passed in as param at the end of specified pile.
   * Checks if move is legal for Foundation pile.
   * If move is legal, the card is moved and True is returned.
   * If the move is illegal, the card is not moved and False is returned.
   * Foundation pile Moves Rules:
   * "A card can be added to a foundation pile if and only if its suit matches that of the pile,
   * and its value is one more than that of the card currently on top of the pile. If a foundation
   * pile is currently empty, any ace can be added to it: there is no required ordering of suits
   * in the foundation piles."
   * @return true if move is legal and made, false if it was not
   */

  @Override
  public boolean put(int pileNumber, Card card) {

    if (card.isOfValue(CardValue.ace)) {

      if (this.pileContents.get(pileNumber).isEmpty()) {
        this.pileContents.get(pileNumber).add(card);
        return true;
      } else {
        return false;
      }

    } else {

      if (this.pileContents.get(pileNumber).isEmpty()) {
        return false;
      }
      Card topCard = this.pileContents.get(pileNumber)
              .get(this.pileContents.get(pileNumber).size() - 1);
      if (topCard.sameSuitAs(card) && card.compare(topCard) == 1) {
        this.pileContents.get(pileNumber).add(card);
        return true;
      } else {
        return false;
      }

    }
  }
}
