package com.formfill.monopoly.game.square;

public class HotelSquare extends PropertySquare {

  public HotelSquare(String name) {
    super(name);
  }

  @Override
  public int getCost() {
    return 1000;
  }

  @Override
  public int getRent() {
    return 300;
  }
}
