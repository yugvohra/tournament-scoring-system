package com.yug.scoringsystem.rules;

import com.yug.scoringsystem.domain.Game;
import com.yug.scoringsystem.domain.GameState;
import com.yug.scoringsystem.domain.GameStatus;
import com.yug.scoringsystem.domain.Player;

import java.util.Optional;

public class NormalGameRuleEngine implements IGameRuleEngine {
  private static NormalGameRuleEngine INSTANCE = new NormalGameRuleEngine();

  private NormalGameRuleEngine() {
  }

  public static NormalGameRuleEngine getInstance() {
    return INSTANCE;
  }

  public GameStatus determineGameState(Game aGame) {
    Optional<Player> leadingPlayer = aGame.getGameScore().getLeadPlayer();
    Long lead = aGame.getGameScore().getLead();
    if (!leadingPlayer.isPresent() || aGame.getGameScore().getPlayerScore(leadingPlayer.get()) < 4)
      return new GameStatus(GameState.UNDECIDED, aGame.getGameScore());
    else if (lead == 0)
      return new GameStatus(GameState.DEUCE, aGame.getGameScore());
    else if (lead == 1)
      return new GameStatus(GameState.ADVANTAGE, aGame.getGameScore());
    else
      return new GameStatus(GameState.WON, aGame.getGameScore());
  }
}