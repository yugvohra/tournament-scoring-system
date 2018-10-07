package com.yug.scoringsystem.domain.game;

import com.yug.scoringsystem.domain.IState;
import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;

import java.util.Optional;

public enum GameState implements IState<GamePoint> {

  UNDECIDED_TIEBREAKER {
    public boolean canTransition() {
      return true;
    }

    public IState nextState(ScoreBoard<GamePoint> scoreboard) {
      Optional<Player> leadingPlayer = scoreboard.getLeadPlayer();
      Long lead = scoreboard.getLead();
      if (lead >= 2 && scoreboard.getPlayerScore(leadingPlayer.get()) >= 7)
        return WON;
      else
        return UNDECIDED_TIEBREAKER;
    }
  },
  UNDECIDED {
    public boolean canTransition() {
      return true;
    }

    public IState nextState(ScoreBoard<GamePoint> scoreboard) {
      Optional<Player> leadingPlayer = scoreboard.getLeadPlayer();
      Long lead = scoreboard.getLead();
      if (lead >= 2 && scoreboard.getPlayerScore(leadingPlayer.get()) > 3)
        return WON;
      else if (leadingPlayer.isPresent() && lead == 0 && scoreboard.getPlayerScore(leadingPlayer.get()) >= 3)
        return DEUCE;
      else
        return UNDECIDED;
    }
  },

  ADVANTAGE {
    public boolean canTransition() {
      return true;
    }

    public IState nextState(ScoreBoard<GamePoint> scoreboard) {
      Long lead = scoreboard.getLead();
      if (lead >= 2)
        return WON;
      else if (lead == 0)
        return DEUCE;
      else
        return ADVANTAGE;
    }
  },
  DEUCE {
    public boolean canTransition() {
      return true;
    }

    public IState nextState(ScoreBoard<GamePoint> scoreboard) {
      Long lead = scoreboard.getLead();
      if (lead >= 1)
        return ADVANTAGE;
      else
        return DEUCE;
    }
  },
  WON {
    public boolean canTransition() {
      return false;
    }

    public IState nextState(ScoreBoard<GamePoint> scoreboard) {
      return WON;
    }
  },
}
