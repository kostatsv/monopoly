package com.formfill.monopoly.game.checks;

import com.formfill.monopoly.game.Monopoly;
import com.formfill.monopoly.game.player.Player;

public class BankruptCheck implements IGameCheck {

  private IGameCheck nextCheck;

  @Override
  public void check(Monopoly monopoly) {
    long bankruptPLayers = monopoly.getPlayers().stream().filter(Player::isBankrupt).count();

    if (bankruptPLayers == monopoly.getPlayers().size()) {
      monopoly.stopGame("All players are bankrupt");
    }
    else if (nextCheck != null) {
      nextCheck.check(monopoly);
    }
  }

  @Override
  public void setNextCheck(IGameCheck nextCheck) {
    this.nextCheck = nextCheck;
  }
}
