package com.yug.scoringsystem.domain;

/**
 * used to beautify the match score
 *
 * */
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