import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.card.Card;
import cs3500.hw03.FreecellController;

/**
 * Created by Matt on 5/22/17.
 */
public class test {
  public static void main(String[] args) {
    FreecellModel model = new FreecellModel();
    List<Card> deck = model.getDeck();

    try {
      new FreecellController(new InputStreamReader(System.in), System.out)
              .playGame(deck, model, 8, 4, false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
