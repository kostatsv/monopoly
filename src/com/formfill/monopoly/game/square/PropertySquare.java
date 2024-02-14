package com.formfill.monopoly.game.square;

import com.formfill.monopoly.game.player.Player;

public abstract class PropertySquare implements Square {

  public PropertySquare(String name) {
    this.name = name;
  }

  public abstract int getCost();

  public abstract int getRent();

  private Player owner;

  private final String name;

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public Player getOwner() {
    return owner;
  }

  public String getName() {
    return name;
  }

  @Override
  public void apply(Player player) {
    if (owner == null) {
      player.buy(this);
    }
    else if (owner != player) {
      player.payRentTo(owner, getRent());
    }
    else {
      // we are the owner of the square
      System.out.printf("This is our property%n");
    }
  }
}
