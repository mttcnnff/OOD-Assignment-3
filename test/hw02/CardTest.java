package hw02;

import cs3500.hw02.card.Card;
import cs3500.hw02.card.CardSuit;
import cs3500.hw02.card.CardValue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Matt on 5/19/17.
 */
public class CardTest {
  Card aceOfSpades = new Card(CardValue.ace, CardSuit.spades);
  Card fiveOfSpades = new Card(CardValue.five, CardSuit.spades);

  Card queenOfHearts = new Card(CardValue.queen, CardSuit.hearts);
  Card eightOfHearts = new Card(CardValue.eight, CardSuit.hearts);

  Card sevenOfClubs = new Card(CardValue.seven, CardSuit.clubs);
  Card jackOfClubs = new Card(CardValue.jack, CardSuit.clubs);

  Card aceOfDiamonds = new Card(CardValue.ace, CardSuit.diamonds);
  Card sevenOfDiamonds = new Card(CardValue.seven, CardSuit.diamonds);

  /*
   *Test card.toString() methods
   */

  //Test toString for lettered cards
  @Test
  public void testToStringLetteredCards() {
    assertEquals("A♠", aceOfSpades.toString());
    assertEquals("Q♥", queenOfHearts.toString());
    assertEquals("J♣", jackOfClubs.toString());
  }

  //Test toString for numbered cards
  @Test
  public void testToStringNumberedCards() {
    assertEquals("5♠", fiveOfSpades.toString());
    assertEquals("8♥", eightOfHearts.toString());
  }

  /*
   *Tests card.isOfSuit for numbered and lettered cards
   */
  @Test
  public void testIsOfSuit() {
    assertEquals(true, sevenOfClubs.isOfSuit(CardSuit.clubs));
    assertNotEquals(true, sevenOfClubs.isOfSuit(CardSuit.hearts));

    assertEquals(true, aceOfSpades.isOfSuit(CardSuit.spades));
  }

  /*
   * Tests card.isOfValue for numbered and lettered cards
   */
  @Test
  public void testIsOfValue() {
    assertEquals(true, aceOfDiamonds.isOfValue(CardValue.ace));
    assertEquals(true, sevenOfDiamonds.isOfValue(CardValue.seven));

    assertNotEquals(true, eightOfHearts.isOfValue(CardValue.seven));
  }

  /*
   * Tests card.sameSuitAs
   */
  @Test
  public void testSameSuitAs() {
    assertEquals(true, aceOfDiamonds.sameSuitAs(sevenOfDiamonds));
    assertNotEquals(true, eightOfHearts.sameSuitAs(fiveOfSpades));
  }

  /*
   * Tests card.sameColorAs
   */
  @Test
  public void testSameColorAs() {
    //different suits same color
    assertEquals(true, aceOfSpades.sameColorAs(jackOfClubs));
    //same suits same color
    assertEquals(true, eightOfHearts.sameColorAs(queenOfHearts));
    //different suits different color
    assertEquals(false, sevenOfClubs.sameColorAs(aceOfDiamonds));
  }

  /*
   * Tests card.compare for value difference
   */
  @Test
  public void testCompare() {
    //same card
    assertEquals(0, fiveOfSpades.compare(fiveOfSpades));
    //different card same value
    assertEquals(0, sevenOfClubs.compare(sevenOfDiamonds));
    //different cards positive result
    assertEquals(4, queenOfHearts.compare(eightOfHearts));
    //different cards negative result
    assertEquals(-10, aceOfSpades.compare(jackOfClubs));
  }


}
