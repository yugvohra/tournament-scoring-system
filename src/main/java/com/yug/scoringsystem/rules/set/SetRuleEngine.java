package com.yug.scoringsystem.rules.set;

import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.game.State;
import com.yug.scoringsystem.domain.game.TangibleStatus;
import com.yug.scoringsystem.domain.set.TennisSet;

import java.util.Optional;

public class SetRuleEngine {

  private static SetRuleEngine INSTANCE = new SetRuleEngine();

  private SetRuleEngine() {

  }

  public static SetRuleEngine getInstance() {
    return INSTANCE;
  }

  public TangibleStatus determineSetState(TennisSet aSet) {
    Optional<Player> leadingPlayer = aSet.getScoreBoard().getLeadPlayer();
    Long lead = aSet.getScoreBoard().getLead();
    if (!leadingPlayer.isPresent() || aSet.getScoreBoard().getPlayerScore(leadingPlayer.get()) < 7 || lead < 2)
      return new TangibleStatus(State.UNDECIDED, aSet.getScoreBoard());
    else
      return new TangibleStatus(State.WON, aSet.getScoreBoard());
  }
}
