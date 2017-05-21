package cs3500.hw02.pile;

import java.util.ArrayList;

import cs3500.hw02.PileType;
import cs3500.hw02.card.Card;

/**
 * Created by Matt on 5/16/17.
 */
public abstract class AbstractPile {
  protected ArrayList<ArrayList<Card>> pileContents;
  private PileType pileType;

  AbstractPile(PileType pileType, int numOfPiles) {
    this.pileType = pileType;
    this.pileContents = new ArrayList<>();

    for (int i = 0; i < numOfPiles; i++) {
      this.pileContents.add(new ArrayList<Card>());
    }
  }

  private void remove(int pileNumber, int index) {
    this.pileContents.get(pileNumber).remove(index);
  }

  protected abstract boolean put(int pileNumber, Card card);

  /**
   * Moves card from this pile to dest Pile according to
   * Freecell game rules. Refer to assignment for those rules.
   * @param sourcePileNumber pile number of source.
   * @param sourceCardIndex index number of card stack.
   * @param dest destination pile for card.
   * @param destPileNumber number of destination pile.
   * @return true if move is made.
   */
  public boolean move(int sourcePileNumber, int sourceCardIndex, AbstractPile dest, int
          destPileNumber) {
    if (this.pileContents.get(sourcePileNumber).size() - 1 != sourceCardIndex) {
      return false;
    }
    if (dest.put(destPileNumber, this.pileContents.get(sourcePileNumber).get(sourceCardIndex))) {
      this.remove(sourcePileNumber, sourceCardIndex);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns string representation of single pile
   * to be used in building full representation of
   * game state.
   * @return string rep of this pile.
   */
  public String getPileState() {
    String state = "";
    int index = 1;
    for (ArrayList<Card> pile : pileContents) {
      state = state + this.pileType.toString() + String.valueOf(index) + ":";
      for (Card card : pile) {
        state = state + " " + card.toString();
        if (card != pile.get(pile.size() - 1)) {
          state = state + ",";
        }
      }
      if (pile != pileContents.get(pileContents.size() - 1)) {
        state = state + "\n";
      }
      index++;
    }
    return state;
  }

  public boolean hasPile(int pileNumber) {
    return pileNumber >= 0 && pileNumber < this.pileContents.size();
  }

  /**
   * @return card count of this pile.
   */
  public int cardCount() {
    int count = 0;
    for (ArrayList<Card> pile : this.pileContents) {
      count = count + pile.size();
    }
    return count;
  }
}
