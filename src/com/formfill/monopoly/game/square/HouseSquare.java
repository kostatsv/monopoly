package com.formfill.monopoly.game.square;

import com.formfill.monopoly.game.player.Player;

public class HouseSquare extends PropertySquare {

  public HouseSquare(String name) {
    super(name);
  }

  @Override
  public int getCost() {
    return 500;
  }

  @Override
  public int getRent() {
    return 100;
  }

}
