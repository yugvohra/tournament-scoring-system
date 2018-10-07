package com.yug.scoringsystem.domain;

/** Interface for state machine*/
public interface IState<T extends IScoredPoint> {
  boolean canTransition();

  IState nextState(ScoreBoard<T> scoreboard);

}
