package com.formfill.monopoly.game.square;

import com.formfill.monopoly.game.player.Player;
import com.formfill.monopoly.game.utils.GameUtils;

public class GoSquare implements Square {

  @Override
  public void apply(Player player) {
    System.out.println(player.getName() + " landed on GO and collects " + GameUtils.AROUND_BOARD_REWARD);
    player.addMoney(GameUtils.AROUND_BOARD_REWARD);
  }

  @Override
  public String getName() {
    return "GO";
  }
}
