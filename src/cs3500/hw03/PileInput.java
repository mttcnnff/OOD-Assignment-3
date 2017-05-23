package cs3500.hw03;

import cs3500.hw02.PileType;

/**
 * Created by Matt on 5/23/17.
 */
public class PileInput extends AbstractInput {

  public PileInput() {
    super();
  }

  @Override
  public boolean isValid() {
    String pattern ="[C|O|F]\\d+";
    if (this.move.matches(pattern)) {
      return true;
    } else {
      return false;
    }
  }

  public PileType getPileType() {
    if (this.isValid()) {
      switch (this.move.substring(0,1)) {
        case "C":
          return PileType.CASCADE;
        case "O":
          return PileType.OPEN;
        case "F":
          return PileType.FOUNDATION;
        default:
          return null;
      }
    } else {
      return null;
    }
  }

  public Integer getPileNumber() {
    if (this.isValid()) {
      return Integer.valueOf(this.move.substring(1));
    } else {
      return 0;
    }
  }
}
