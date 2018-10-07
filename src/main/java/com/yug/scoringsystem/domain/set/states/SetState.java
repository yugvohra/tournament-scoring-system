package com.yug.scoringsystem.domain.set.states;

import com.yug.scoringsystem.domain.IState;
import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.set.SetPoint;

import java.util.Optional;

public enum SetState implements IState<SetPoint> {
  UNDECIDED() {
    public boolean canTransition() {
      return true;
    }

    public IState nextState(ScoreBoard<SetPoint> scoreboard) {
      Optional<Player> leadingPlayer = scoreboard.getLeadPlayer();
      Long lead = scoreboard.getLead();
      if (leadingPlayer.isPresent() && scoreboard.getPlayerScore(leadingPlayer.get()) >= 6) {
        if (lead >= 2 || scoreboard.getPlayerScore(leadingPlayer.get()) == 7)
          return WON;
        else if (lead == 0)
          return TIE;
      }
      return UNDECIDED;
    }
  },

  TIE {
    public boolean canTransition() {
      return true;
    }

    public IState nextState(ScoreBoard<SetPoint> scoreboard) {
      Optional<Player> leadingPlayer = scoreboard.getLeadPlayer();
      if (leadingPlayer.isPresent() && scoreboard.getPlayerScore(leadingPlayer.get()) == 7)
        return WON;
      return TIE;
    }
  },

  WON {
    public boolean canTransition() {
      return false;
    }

    public IState nextState(ScoreBoard<SetPoint> scoreboard) {
      return WON;
    }
  }
}
