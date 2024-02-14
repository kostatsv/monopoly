package com.formfill.monopoly.game.checks;

import com.formfill.monopoly.game.Monopoly;
import com.formfill.monopoly.game.utils.GameUtils;

public class AllLapsCheck implements IGameCheck {

  private IGameCheck nextCheck;

  @Override
  public void check(Monopoly monopoly) {
    long numberOfPlayersWithMaxLaps =
      monopoly.getPlayers().stream().filter(player -> player.getLaps() >= GameUtils.MAX_LAPS).count();

    if (numberOfPlayersWithMaxLaps == monopoly.getPlayers().size()) {
      monopoly.stopGame("Max laps reached");
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
