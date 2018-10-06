package com.yug.scoringsystem.rules;

import com.yug.scoringsystem.domain.Game;
import com.yug.scoringsystem.domain.GameState;
import com.yug.scoringsystem.domain.GameStatus;
import com.yug.scoringsystem.domain.Player;

import java.util.Optional;

public class TieBreakerRuleEngine implements IGameRuleEngine {
  @Override
  public GameStatus determineGameState(Game aGame) {
    Optional<Player> leadingPlayer = aGame.getGameScore().getLeadPlayer();
    Long lead = aGame.getGameScore().getLead();
    if (!leadingPlayer.isPresent() || aGame.getGameScore().getPlayerScore(leadingPlayer.get()) < 7 || lead < 2)
      return new GameStatus(GameState.UNDECIDED, aGame.getGameScore());
    else
      return new GameStatus(GameState.WON, aGame.getGameScore());
  }
}
