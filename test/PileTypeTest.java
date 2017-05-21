/**
 * Created by Matt on 5/19/17.
 */

import org.junit.Test;

import cs3500.hw02.PileType;

import static org.junit.Assert.assertEquals;

public class PileTypeTest {
  /*
   * Tests PileType.toString for OPEN, CASCADE, and FOUNDATION PILES
   */
  @Test
  public void testToString() {
    assertEquals("O", PileType.OPEN.toString());
    assertEquals("C", PileType.CASCADE.toString());
    assertEquals("F", PileType.FOUNDATION.toString());
  }
}
