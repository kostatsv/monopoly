package com.formfill.monopoly.game.square;

import com.formfill.monopoly.game.player.Player;

public class JailSquare implements Square {

  @Override
  public void apply(Player player) {
    System.out.println(player.getName() + " is in jail!");
    player.setSkipTurn(true);
  }

  @Override
  public String getName() {
    return "Jail";
  }
}
