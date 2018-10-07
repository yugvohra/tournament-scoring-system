package com.yug.scoringsystem.domain.game;

import com.yug.scoringsystem.domain.IState;
import com.yug.scoringsystem.domain.ScoreBoard;

public class TangibleStatus {
  private final IState state;
  private final ScoreBoard scoreBoard;

  public TangibleStatus(IState state, ScoreBoard scoreBoard) {
    this.state = state;
    this.scoreBoard = scoreBoard;
  }

  public IState getState() {
    return state;
  }

  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }
}