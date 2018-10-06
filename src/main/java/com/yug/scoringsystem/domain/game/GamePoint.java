package com.yug.scoringsystem.domain.game;

import com.yug.scoringsystem.domain.IScoredPoint;
import com.yug.scoringsystem.domain.Player;

public class GamePoint implements IScoredPoint {
  /**
   * Maintaining Bilateral relationship for the time being
   */
  private final Player scoringPlayer;

  public GamePoint(Player scoringPlayer) {
    this.scoringPlayer = scoringPlayer;
  }


  public Player getScoringPlayer() {
    return scoringPlayer;
  }
}
