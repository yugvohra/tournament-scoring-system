package com.yug.scoringsystem.rules.game;

import com.yug.scoringsystem.domain.game.GameType;

public class GameRuleEngineFactory {
  private static GameRuleEngineFactory INSTANCE = new GameRuleEngineFactory();

  /**
   * Keeping both the ruleEngine as singleton as of now
   */
  private static NormalGameRuleEngine normalGameRuleEngine = new NormalGameRuleEngine();
  private static TieBreakerRuleEngine tieBreakerRuleEngine = new TieBreakerRuleEngine();

  private GameRuleEngineFactory() {
  }

  public static GameRuleEngineFactory getInstance() {
    return INSTANCE;
  }

  public IGameRuleEngine getRuleEngine(GameType gameType) {
    switch (gameType) {
      case TIE_BREAKER:
        return tieBreakerRuleEngine;
      default:
        return normalGameRuleEngine;
    }
  }
}
