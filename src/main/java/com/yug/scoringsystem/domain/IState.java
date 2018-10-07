package com.yug.scoringsystem.domain;

public interface IState<T extends IScoredPoint> {
  boolean canTransition();

  IState nextState(ScoreBoard<T> scoreboard);

}
