package com.formfill.monopoly.game.checks;

import com.formfill.monopoly.game.Monopoly;

public interface IGameCheck {

  void check(Monopoly monopoly);

  void setNextCheck(IGameCheck nextCheck);

}
