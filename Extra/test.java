package cs3500.hw03;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.card.Card;
import cs3500.hw03.FreecellController;

/**
 * Created by Matt on 5/22/17.
 */
public class test {
  public static void main(String[] args) {
    String gameplay = "";

    for (int i = 0; i < 52; i++) {
      int cascnum = (i%8) + 1;
      Double d = Math.ceil((52-(i+1))/8) + 1;
      Integer indexnum = d.intValue();
      int foundnum = (i%4 + 1);
      gameplay = gameplay + "C" + String.valueOf(cascnum) + " " + String.valueOf(indexnum) + " F"
              + String.valueOf(foundnum) + "\n";
    }

    System.out.println(gameplay);

    FreecellModel model = new FreecellModel();
    List<Card> deck = model.getDeck();
    new FreecellController(new InputStreamReader(System.in), System.out)
            .playGame(deck, model, 8, 4, false);


  }
}
