package com.yug.scoringsystem.rules.game;

import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.TangibleStatus;
import com.yug.scoringsystem.domain.Player;

import java.util.Optional;

public class TieBreakerRuleEngine implements IGameRuleEngine {

  TieBreakerRuleEngine() {
  }

  @Override
  public TangibleStatus determineGameState(Game aGame) {
    Optional<Player> leadingPlayer = aGame.getScoreBoard().getLeadPlayer();
    Long lead = aGame.getScoreBoard().getLead();
    if (!leadingPlayer.isPresent() || aGame.getScoreBoard().getPlayerScore(leadingPlayer.get()) < 7 || lead < 2)
      return new TangibleStatus(State.UNDECIDED, aGame.getScoreBoard());
    else
      return new TangibleStatus(State.WON, aGame.getScoreBoard());
  }
}
