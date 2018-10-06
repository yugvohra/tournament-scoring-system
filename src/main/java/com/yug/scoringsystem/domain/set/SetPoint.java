package com.yug.scoringsystem.domain.set;

import com.yug.scoringsystem.domain.IScoredPoint;
import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.game.Game;

public class SetPoint implements IScoredPoint {
  private final Game wonGame;
  private final Player scoringPlayer;

  public SetPoint(Game wonGame, Player scoringPlayer) {
    this.wonGame = wonGame;
    this.scoringPlayer = scoringPlayer;
  }

  @Override
  public Player getScoringPlayer() {
    return scoringPlayer;
  }
}
