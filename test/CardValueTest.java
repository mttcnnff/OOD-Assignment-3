/**
 * Created by Matt on 5/19/17.
 */

import org.junit.Test;

import cs3500.hw02.card.CardValue;

import static org.junit.Assert.assertEquals;

public class CardValueTest {

  /*
   * Tests CardValue.getCardValue for numbered and lettered cards
   */
  @Test
  public void testGetCardValue() {
    //Numbered
    assertEquals(8, CardValue.eight.getCardValue());
    //Lettered
    assertEquals(13, CardValue.king.getCardValue());
  }

  /*
   * Tests CardValue.toString for numbered and lettered cards
   */
  @Test
  public void testToString() {
    //Numbered
    assertEquals("8", CardValue.eight.toString());
    //Lettered
    assertEquals("K", CardValue.king.toString());
  }
}
