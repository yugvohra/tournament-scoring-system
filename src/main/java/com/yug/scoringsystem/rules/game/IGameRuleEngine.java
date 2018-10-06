package com.yug.scoringsystem.rules.game;

import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.TangibleStatus;

public interface IGameRuleEngine {
  TangibleStatus determineGameState(Game aGame);
}
