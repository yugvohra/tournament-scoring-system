package com.yug.scoringsystem.rules;

import com.yug.scoringsystem.domain.Game;
import com.yug.scoringsystem.domain.GameState;

public class GameRuleEngine {
  private static GameRuleEngine INSTANCE = new GameRuleEngine();

  private GameRuleEngine() {
  }

  public static GameRuleEngine getInstance() {
    return INSTANCE;
  }

  public GameState determineGameState(Game aGame) {
    return aGame.getLead() < 2 ? GameState.UNDECIDED : null;
  }
}