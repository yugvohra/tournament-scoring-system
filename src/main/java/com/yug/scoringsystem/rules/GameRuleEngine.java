package com.yug.scoringsystem.rules;

import com.yug.scoringsystem.domain.Game;
import com.yug.scoringsystem.domain.GameState;
import com.yug.scoringsystem.domain.Player;

import java.util.Optional;

public class GameRuleEngine {
  private static GameRuleEngine INSTANCE = new GameRuleEngine();

  private GameRuleEngine() {
  }

  public static GameRuleEngine getInstance() {
    return INSTANCE;
  }

  public GameState determineGameState(Game aGame) {
    Optional<Player> leadingPlayer = aGame.getLeadingPlayer();
    Long lead = aGame.getLead();
    if (!leadingPlayer.isPresent() || aGame.getPlayerScore(leadingPlayer.get()) < 4)
      return GameState.UNDECIDED;
    else if (lead == 0)
      return GameState.DEUCE;
    else if (lead == 1)
      return GameState.ADVANTAGE;
    else
      return GameState.WON;
  }
}