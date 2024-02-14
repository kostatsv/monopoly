package com.formfill.monopoly.game.square;

import com.formfill.monopoly.game.player.Player;

public interface Square {

  void apply(Player player);

  String getName();

}
