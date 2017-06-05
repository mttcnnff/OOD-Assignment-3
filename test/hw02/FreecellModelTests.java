package hw02; /**
 * Created by Matt on 5/19/17.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.card.Card;
import cs3500.hw02.card.CardSuit;
import cs3500.hw02.card.CardValue;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;

import static org.junit.Assert.assertEquals;

public class FreecellModelTests {
  FreecellModel testGame = new FreecellModel();
  List<Card> deck = testGame.getDeck();
  List<Card> badDeck;
  List<Card> emptyDeck;


  /*
   *Tests that getDeck returns same unshuffled deck everytime
   * with 52 cards
   */
  @Test
  public void testGetDeck() {
    //assertArrayEquals(deck.toArray(), testGame.getDeck().toArray());
    assertEquals(52, testGame.getDeck().size());
  }

  /*
   * Tests FreecellModel.startGame with Invalid deck
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartGame1() {
    this.buildBadDeck();
    testGame.startGame(this.badDeck, 8, 4, false);
  }

  /*
   * Tests FreecellModel.startGame with empty deck
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartGame2() {
    this.buildEmptyDeck();
    testGame.startGame(this.emptyDeck, 8, 4, false);
  }

  /*
   * Tests FreecellModel.startGame with invalid cascadePile nums
   */

  //negative case
  @Test(expected = IllegalArgumentException.class)
  public void testStartGame3() {
    testGame.startGame(this.deck, -1, 4, false);
  }

  //positive case less than 4
  @Test(expected = IllegalArgumentException.class)
  public void testStartGame4() {
    testGame.startGame(this.deck, 3, 4, false);
  }

  //zero case
  @Test(expected = IllegalArgumentException.class)
  public void testStartGame5() {
    testGame.startGame(this.deck, 0, 4, false);
  }


  /*
   * Tests FreecellModel.startGame with invalid openPile nums
   */

  //negative case
  @Test(expected = IllegalArgumentException.class)
  public void testStartGame7() {
    testGame.startGame(this.deck, 8, -1, false);
  }

  //zero case
  @Test(expected = IllegalArgumentException.class)
  public void testStartGame8() {
    testGame.startGame(this.deck, 8, 0, false);
  }


  /*
   * Tests move method
   */

  //tests invalid pileNumber input in negative case
  @Test(expected = IllegalArgumentException.class)
  public void testMove1() {
    this.testGame.startGame(this.deck, 8, 4, false);
    this.testGame.move(PileType.CASCADE, -1, 4, PileType.OPEN, 0);

  }

  //tests invalid pileNumber input in case where the pile doesn't exist
  @Test(expected = IllegalArgumentException.class)
  public void testMove2() {
    this.testGame.startGame(this.deck, 7, 4, false);
    this.testGame.move(PileType.CASCADE, 8, 4, PileType.OPEN, 0);
  }

  //tests invalid destPileNumber input in negative case
  @Test(expected = IllegalArgumentException.class)
  public void testMove3() {
    this.testGame.startGame(this.deck, 8, 4, false);
    this.testGame.move(PileType.CASCADE, 0, 4, PileType.OPEN, -1);
  }

  //tests invalid destPileNumber input in case where the pile doesn't exist
  @Test(expected = IllegalArgumentException.class)
  public void testMove4() {
    this.testGame.startGame(this.deck, 7, 3, false);
    this.testGame.move(PileType.CASCADE, 0, 4, PileType.OPEN, 3);
  }

  //tests invalid move when trying to move card that isn't last on pile
  @Test(expected = IllegalArgumentException.class)
  public void testMove5() {
    this.testGame.startGame(this.deck, 8, 4, false);
    this.testGame.move(PileType.CASCADE, 0, 3, PileType.OPEN, 0);
  }

  //tests invalid move when trying to move card with negative cardIndex
  @Test(expected = IllegalArgumentException.class)
  public void testMove6() {
    this.testGame.startGame(this.deck, 8, 4, false);
    this.testGame.move(PileType.CASCADE, 0, -1, PileType.OPEN, 0);
  }

  //tests valid move of stacking three cards on foundation pile
  //of both letter and number value
  @Test
  public void testMove7() {
    this.testGame.startGame(this.deck, 8, 4, false);
    this.testGame.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
    this.testGame.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 0);
    this.testGame.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    assertEquals("F1: A♣, 2♣, 3♣\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C5: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥", this.testGame.getGameState());
  }

  /*tests invalid move when trying to put card that isn't ace
   *on empty foundation pile
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMove8() {
    this.testGame.startGame(this.deck, 8, 4, false);
    this.testGame.move(PileType.CASCADE, 4, 6, PileType.FOUNDATION, 0);
  }

  /*tests invalid move when trying to put card of value not greater than 1
   * on foundation pile
   */

  //value difference is greater than 1
  @Test(expected = IllegalArgumentException.class)
  public void testMove9() {
    this.testGame.startGame(this.deck, 8, 4, false);
    this.testGame.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    this.testGame.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    this.testGame.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    this.testGame.move(PileType.CASCADE, 1, 6, PileType.OPEN, 3);
    this.testGame.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    this.testGame.move(PileType.CASCADE, 1, 5, PileType.FOUNDATION, 0);
    this.testGame.move(PileType.CASCADE, 0, 4, PileType.FOUNDATION, 0);
  }

  /*
   * Trying to put more than one card on open pile
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMove10() {
    this.testGame.startGame(this.deck, 8, 4, false);
    this.testGame.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    this.testGame.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    this.testGame.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    this.testGame.move(PileType.CASCADE, 1, 6, PileType.OPEN, 3);
    this.testGame.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    this.testGame.move(PileType.CASCADE, 1, 5, PileType.FOUNDATION, 0);
    this.testGame.move(PileType.CASCADE, 0, 4, PileType.OPEN, 0);
  }

  /*
   * Trying to move a card when game is not built yet
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMove11() {
    this.testGame = new FreecellModel();
    this.testGame.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
  }

  /*
   * Tests isGameOver after normal start game
   */
  @Test
  public void testIsGameOver() {
    this.testGame.startGame(this.deck, 8, 4, false);
    assertEquals(false, this.testGame.isGameOver());
  }

  /*
   * Tests isGameOver when game has not been started yet
   */
  @Test
  public void testIsGameOver1() {
    this.testGame = new FreecellModel();
    assertEquals(false, this.testGame.isGameOver());
  }

  /*
   * Tests FreecellModel.getGameState on unshuffled game and after a move
   */
  @Test
  public void testGetGameState1() {
    this.testGame.startGame(this.deck, 8, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C5: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥", this.testGame.getGameState());
    this.testGame.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: A♣\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C5: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥", this.testGame.getGameState());

  }

  /*
   * Tests getGameState before game is started
   */
  @Test
  public void testGetGameState2() {
    this.testGame = new FreecellModel();
    assertEquals("", this.testGame.getGameState());
  }


  private void buildBadDeck() {
    this.badDeck = new ArrayList<Card>();
    this.badDeck.add(new Card(CardValue.seven, CardSuit.hearts));
    this.badDeck.add(new Card(CardValue.jack, CardSuit.spades));
    this.badDeck.add(new Card(CardValue.queen, CardSuit.hearts));
    this.badDeck.add(new Card(CardValue.ace, CardSuit.diamonds));
    this.badDeck.add(new Card(CardValue.king, CardSuit.clubs));
  }

  private void buildEmptyDeck() {
    this.emptyDeck = new ArrayList<Card>();
  }
}


