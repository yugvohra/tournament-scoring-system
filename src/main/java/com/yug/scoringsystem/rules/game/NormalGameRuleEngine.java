package com.yug.scoringsystem.rules.game;

import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.TangibleStatus;

import java.util.Optional;

public class NormalGameRuleEngine implements IGameRuleEngine {

  NormalGameRuleEngine() {
  }

  public TangibleStatus determineGameState(Game aGame) {
    Optional<Player> leadingPlayer = aGame.getScoreBoard().getLeadPlayer();
    Long lead = aGame.getScoreBoard().getLead();
    if (!leadingPlayer.isPresent() || aGame.getScoreBoard().getPlayerScore(leadingPlayer.get()) < 4)
      return new TangibleStatus(State.UNDECIDED, aGame.getScoreBoard());
    else if (lead == 0)
      return new TangibleStatus(State.DEUCE, aGame.getScoreBoard());
    else if (lead == 1)
      return new TangibleStatus(State.ADVANTAGE, aGame.getScoreBoard());
    else
      return new TangibleStatus(State.WON, aGame.getScoreBoard());
  }
}