/**
 * Created by Matt on 5/19/17.
 */

import org.junit.Test;

import java.util.List;

import cs3500.hw02.card.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.pile.CascadePile;
import cs3500.hw02.pile.FoundationPile;
import cs3500.hw02.pile.OpenPile;

import static org.junit.Assert.assertEquals;

public class AbstractPileTest {
  FreecellModel testGame = new FreecellModel();
  List<Card> deck = testGame.getDeck();
  OpenPile open = new OpenPile(4);
  CascadePile cascade = new CascadePile(8, deck);
  FoundationPile foundation = new FoundationPile();


  /*
   * Tests AbstractPile.cardCount for all piles
   */
  @Test
  public void testCardCount() {
    //All cards should be in Cascade pile
    assertEquals(52, cascade.cardCount());
    //Check empty piles
    assertEquals(0, open.cardCount());
    assertEquals(0, foundation.cardCount());
    //move card from cascade to open pile
    cascade.move(0, 6, open, 0);
    //check that card has been added to open pile
    assertEquals(1, open.cardCount());
    cascade.move(0, 5, open, 1);
    assertEquals(2, open.cardCount());
    assertEquals(50, cascade.cardCount());
  }

  /*
   * Tests AbstractPile.hasPile();
   */
  @Test
  public void testHasPile() {
    resetPiles();
    //Test they all have first pile
    assertEquals(true, cascade.hasPile(0));
    assertEquals(true, open.hasPile(0));
    assertEquals(true, foundation.hasPile(0));

    //Test bad pileNumber inputs
    assertEquals(false, cascade.hasPile(-1));
    assertEquals(false, foundation.hasPile(100));
  }

  /*
   * Tests AbstractPile.getPileState
   */
  @Test
  public void testGetPileState() {
    resetPiles();
    //Tests full cascade pile with NO NEW LINE AT END
    assertEquals("C1: A♥, 9♥, 4♦, Q♦, 7♠, 2♣, 10♣\n" +
            "C2: 2♥, 10♥, 5♦, K♦, 8♠, 3♣, J♣\n" +
            "C3: 3♥, J♥, 6♦, A♠, 9♠, 4♣, Q♣\n" +
            "C4: 4♥, Q♥, 7♦, 2♠, 10♠, 5♣, K♣\n" +
            "C5: 5♥, K♥, 8♦, 3♠, J♠, 6♣\n" +
            "C6: 6♥, A♦, 9♦, 4♠, Q♠, 7♣\n" +
            "C7: 7♥, 2♦, 10♦, 5♠, K♠, 8♣\n" +
            "C8: 8♥, 3♦, J♦, 6♠, A♣, 9♣", cascade.getPileState());

    //Tests empty cascade pile with NO NEW LINE AT THE END OF EACH LINE
    assertEquals("O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:", open.getPileState());
  }

  /*
   * Tests AbstractPile.move();
   */
  @Test
  public void testMove() {
    //Make legal move
    assertEquals(true, cascade.move(0, 6, open, 0));

    //Make illegal move
    assertEquals(false, open.move(0, 0, cascade, 0));

  }

  /*
   * Util method to reset piles
   */
  private void resetPiles() {
    this.open = new OpenPile(4);
    this.cascade = new CascadePile(8, this.deck);
    this.foundation = new FoundationPile();
  }

}
