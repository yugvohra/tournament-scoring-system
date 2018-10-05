package com.yug.scoringsystem.domain;

public class GamePoint {
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
