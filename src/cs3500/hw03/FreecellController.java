package cs3500.hw03;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.card.Card;
import cs3500.hw03.input.CardIndexInput;
import cs3500.hw03.input.PileInput;

/**
 * Created by Matt on 5/21/17.
 */
public class FreecellController implements IFreecellController<Card> {

  private Readable in;
  private Appendable out;
  private boolean quit;
  private Scanner scan;
  private PileInput sourcePileEntry;
  private CardIndexInput cardIndexEntry;
  private PileInput destPileEntry;

  /**
   * Constructor for FreecellController.
   *
   * @param rd Readable object used to read integers and Strings.
   * @param ap Appendable object used to transmit String.
   */
  public FreecellController(Readable rd, Appendable ap) {
    this.in = rd;
    this.out = ap;
    this.quit = false;
    this.scan = new Scanner(this.in);
    this.sourcePileEntry = new PileInput();
    this.cardIndexEntry = new CardIndexInput();
    this.destPileEntry = new PileInput();
  }

  /**
   * This method should start a new game of Freecell using the provided model, number of cascade
   * and open piles and the provided deck. If "shuffle" is set to false the deck must be used as-is,
   * else the deck should be shuffled.<br><br>
   * Ask provided model to start a new game with given parameters then run game as follows.
   * <br>
   * <br>
   * 1. Transmit game state to the Appendable object exactly as the model provides it.
   * 2. If game is ongoing, wait for user input from the Readable object.
   * <br>
   * i. The source pile (e.g., "C1", as a single word). The pile number begins at 1, so that it is
   * more human-friendly.
   * ii. The card index, again with the index beginning at 1.
   * iii. The destination pile (e.g., "F2", as a single word). The pile number is again counted
   * from 1.
   * <br>
   * 3. If the game has been won, the method should transmit the final game state, and a message
   * "Game over." on a new line and return.
   *
   * @param deck        deck provided to make game with.
   * @param model       model provided to run game.
   * @param numCascades number of cascade piles in this game.
   * @param numOpens    number of open piles in this game.
   * @param shuffle     boolean indicating whether to shuffle deck or not.
   * @throws IllegalStateException thrown only if the controller has not been initialized properly
   *                               to receive input and transmit output.
   */
  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
                       int numOpens, boolean shuffle) throws IllegalStateException,
          IllegalArgumentException, IOException {

    if(this.in == null) {
      throw new IllegalStateException("Readable not initialized.");
    }

    if(this.out == null) {
      throw new IllegalStateException("Appendable not initialized.");
    }

    if(deck == null) {
      throw new IllegalArgumentException("Deck is null!");
    }

    if(model == null) {
      throw new IllegalArgumentException("Model is null!");
    }

    //start game
    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e){
      out.append("\nCould not start game.");
      return;
    }

    //run game
    while (!this.quit && !model.isGameOver()) {
        this.out.append(model.getGameState());

        this.sourcePileEntry.read(scan.next());
        if(this.sourcePileEntry.quitCheck()) {
          this.quit = true;
          break;
        }
        while(!this.sourcePileEntry.isValid()) {
          this.out.append("Enter Source Pile Again: ");
          this.sourcePileEntry.read(scan.next());
        }

        this.cardIndexEntry.read(scan.next());
        if(this.cardIndexEntry.quitCheck()) {
          this.quit = true;
          break;
        }
        while(!this.cardIndexEntry.isValid()) {
          this.out.append("Enter Card Index Again: ");
          this.cardIndexEntry.read(scan.next());
        }

        this.destPileEntry.read(scan.next());
        if(this.destPileEntry.quitCheck()) {
          this.quit = true;
          break;
        }
        while(!this.destPileEntry.isValid()) {
          this.out.append("Enter Destination Pile Again:");
          this.destPileEntry.read(scan.next());
        }

        try {
          model.move(this.sourcePileEntry.getPileType(),
                  this.sourcePileEntry.getPileNumber()-1,
                  this.cardIndexEntry.getCardIndex()-1,
                  this.destPileEntry.getPileType(),
                  this.destPileEntry.getPileNumber()-1);
        } catch (IllegalArgumentException e) {
          this.out.append("\nInvalid Move. Try Again.: " + e.getMessage() + "\n");
        }
    }
    if (this.quit) {
      this.out.append("\nGame quit prematurely.");
      return;
    }
    if (model.isGameOver()) {
      this.out.append("\nGame over.");
      return;
    }

  }
}
