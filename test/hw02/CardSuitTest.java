package hw02; /**
 * Created by Matt on 5/19/17.
 */

import org.junit.Test;

import cs3500.hw02.card.CardSuit;

import static org.junit.Assert.assertEquals;

public class CardSuitTest {

  /*
   * Tests CardSuit.sameColorAs for same and different suits
   */
  @Test
  public void testSameColorAs() {
    //same suit
    assertEquals(true, CardSuit.clubs.sameColorAs(CardSuit.clubs));
    //different suits same color
    assertEquals(true, CardSuit.hearts.sameColorAs(CardSuit.diamonds));
    //different suits different color
    assertEquals(false, CardSuit.hearts.sameColorAs(CardSuit.spades));
  }

  /*
   * Tests CardSuit.toString for all suits
   */
  @Test
  public void testToString() {
    assertEquals("♥", CardSuit.hearts.toString());
    assertEquals("♦", CardSuit.diamonds.toString());
    assertEquals("♠", CardSuit.spades.toString());
    assertEquals("♣", CardSuit.clubs.toString());
  }
}
