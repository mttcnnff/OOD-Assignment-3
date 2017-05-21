package cs3500.hw02.pile;

import cs3500.hw02.PileType;
import cs3500.hw02.card.Card;

/**
 * Created by Matt on 5/16/17.
 */
public class OpenPile extends AbstractPile {

  public OpenPile(int numOfPiles) {
    super(PileType.OPEN, numOfPiles);
  }

  /**
   * Tries to put card passed in as param at the end of specified pile.
   * Checks if move is legal for Open pile.
   * If move is legal, the card is moved and True is returned.
   * If the move is illegal, the card is not moved and False is returned.
   * Open pile Moves Rules:
   * "An open pile may contain at most one card."
   * @return true if move is legal and made, false if it was not
   */

  @Override
  public boolean put(int pileNumber, Card card) {
    if (this.pileContents.get(pileNumber).isEmpty()) {
      this.pileContents.get(pileNumber).add(card);
      return true;
    } else {
      return false;
    }
  }
}
