package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import cs3500.hw02.card.Card;
import cs3500.hw02.card.CardSuit;
import cs3500.hw02.card.CardValue;
import cs3500.hw02.pile.AbstractPile;
import cs3500.hw02.pile.CascadePile;
import cs3500.hw02.pile.FoundationPile;
import cs3500.hw02.pile.OpenPile;

/**
 * Created by Matt on 5/14/17.
 */
public class FreecellModel implements FreecellOperations<Card> {

  private List<Card> deck;
  private HashMap<PileType, AbstractPile> piles;
  boolean gameStarted;

  /**
   * Constructor for Freecell model.
   */
  public FreecellModel() {
    this.deck = new ArrayList<Card>();
    this.piles = new HashMap<>();
    this.gameStarted = false;
  }

  @Override
  public List<Card> getDeck() {
    ArrayList<Card> cards = new ArrayList<>();

    for (int i = 12; i >= 0; i--) {
      for (int j = 3; j >= 0; j--) {
        cards.add(new Card(CardValue.values()[i], CardSuit.values()[j]));
      }
    }

    return cards;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
          throws IllegalArgumentException {

    //Check if deck is valid
    if (this.deckIsInvalid(deck)) {
      throw new IllegalArgumentException("Deck is invalid.");
    } else {
      this.deck = deck;
    }

    //Check if deck should be shuffled
    if (shuffle) {
      Collections.shuffle(this.deck);
    }

    this.piles = new HashMap<>();

    //Build foundation piles
    this.piles.put(PileType.FOUNDATION, new FoundationPile());

    //Check if open pile input is invalid
    if (this.openPileIsInvalid(numOpenPiles)) {
      throw new IllegalArgumentException("Number of Open Piles Invalid.");
    } else {
      this.piles.put(PileType.OPEN, new OpenPile(numOpenPiles));
    }

    //Check if cascade pile input is invalid
    if (this.cascadePileIsInvalid(numCascadePiles)) {
      throw new IllegalArgumentException("Number of Cascade Piles Invalid.");
    } else {
      this.piles.put(PileType.CASCADE, new CascadePile(numCascadePiles, this.deck));
    }

    this.gameStarted = true;

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {
    if (!this.gameStarted) {
      throw new IllegalArgumentException("Cannot move, game is not started");
    }

    if (!this.piles.get(source).hasPile(pileNumber)) {
      throw new IllegalArgumentException("Invalid pile Number for Source.");
    }

    if (!this.piles.get(destination).hasPile(destPileNumber)) {
      throw new IllegalArgumentException("Invalid pile Number for Destination.");
    }

    if (!this.piles.get(source).move(pileNumber, cardIndex, this.piles.get(destination),
            destPileNumber)) {
      throw new IllegalArgumentException("Move not allowed.");
    }

  }

  @Override
  public boolean isGameOver() {
    return this.gameStarted && this.piles.get(PileType.FOUNDATION).cardCount() == 52;
  }

  @Override
  public String getGameState() {
    if (!this.gameStarted) {
      return "";
    }
    return this.piles.get(PileType.FOUNDATION).getPileState()
            + "\n"
            + this.piles.get(PileType.OPEN).getPileState()
            + "\n"
            + this.piles.get(PileType.CASCADE).getPileState();
  }

  private boolean deckIsInvalid(List<Card> deck) {
    //Check deck size
    if (deck.size() != 52) {
      return true;
    }

    //Check duplicates by suit
    for (int i = 0; i < 4; i++) {
      if (this.suitCount(CardSuit.values()[i], deck) != 13) {
        return true;
      }
    }

    //Check duplicated by number
    for (int i = 0; i < 13; i++) {
      if (this.valueCount(CardValue.values()[i], deck) != 4) {
        return true;
      }
    }
    return false;
  }

  private boolean cascadePileIsInvalid(int numCascadePiles) {
    return numCascadePiles < 4;
  }

  private boolean openPileIsInvalid(int numOpenPiles) {
    return numOpenPiles < 1;
  }

  private int suitCount(CardSuit suit, List<Card> deckToCheck) {
    int count = 0;
    for (Card c : deckToCheck) {
      if (c.isOfSuit(suit)) {
        count++;
      }
    }
    return count;
  }

  private int valueCount(CardValue value, List<Card> deckToCheck) {
    int count = 0;
    for (Card c : deckToCheck) {
      if (c.isOfValue(value)) {
        count++;
      }
    }
    return count;
  }
}
