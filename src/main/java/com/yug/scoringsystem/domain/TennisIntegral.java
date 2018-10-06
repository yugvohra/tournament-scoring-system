package com.yug.scoringsystem.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TennisIntegral<T extends IScoredPoint> {

  private Set<T> points;
  private ScoreBoard<T> scoreBoard;

  public TennisIntegral(Player playerOne, Player playerTwo) {
    initialize(playerOne, playerTwo);
  }

  public Set<T> getPoints() {
    return points;
  }

  public ScoreBoard<T> getScoreBoard() {
    return scoreBoard;
  }

  public void addAPoint(T aPoint) {
    points.add(aPoint);
    this.getScoreBoard().addPoint(aPoint);
  }

  private void initialize(Player playerOne, Player playerTwo) {
    points = new HashSet<>(16);
    List<Player> participatingPlayers = new ArrayList<>();
    participatingPlayers.add(playerOne);
    participatingPlayers.add(playerTwo);
    scoreBoard = new ScoreBoard<T>(participatingPlayers);
  }
}
