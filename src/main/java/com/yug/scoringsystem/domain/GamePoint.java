package com.yug.scoringsystem.domain;

public class GamePoint {
  /**
   * Maintaining Bilateral relationship for the time being
   */
  private final Game game;
  private final Player scoringPlayer;

  public GamePoint(Game game, Player scoringPlayer) {
    this.game = game;
    this.scoringPlayer = scoringPlayer;
  }

  public Game getGame() {
    return game;
  }

  public Player getScoringPlayer() {
    return scoringPlayer;
  }
}
