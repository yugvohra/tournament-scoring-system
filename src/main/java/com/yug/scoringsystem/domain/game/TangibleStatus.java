package com.yug.scoringsystem.domain.game;

import com.yug.scoringsystem.domain.ScoreBoard;

public class TangibleStatus {
  private final State state;
  private final ScoreBoard scoreBoard;

  public TangibleStatus(State state, ScoreBoard scoreBoard) {
    this.state = state;
    this.scoreBoard = scoreBoard;
  }

  public State getState() {
    return state;
  }

  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }
}