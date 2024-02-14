package com.formfill.monopoly.game.square;

public class ApartmentSquare extends PropertySquare {

  public ApartmentSquare(String name) {
    super(name);
  }

  @Override
  public int getCost() {
    return 750;
  }

  @Override
  public int getRent() {
    return 250;
  }
}
