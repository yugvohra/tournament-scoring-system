package com.yug.scoringsystem.domain;

import java.util.HashSet;
import java.util.Set;

public class TennisIntegral<T extends IScoredPoint> {

  private Set<T> points;
  private ScoreBoard<T> scoreBoard;

  public TennisIntegral(Player playerOne, Player playerTwo) {
    initialize(playerOne, playerTwo);
  }

  public TennisIntegral(Set<Player> players) {
    assembleIntegrals(players);
  }

  public Set<T> getPoints() {
    return points;
  }

  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }

  public void addAPoint(T aPoint) {
    points.add(aPoint);
    this.getScoreBoard().addPoint(aPoint);
  }

  private void initialize(Player playerOne, Player playerTwo) {
    Set<Player> participatingPlayers = new HashSet<>();
    participatingPlayers.add(playerOne);
    participatingPlayers.add(playerTwo);
    assembleIntegrals(participatingPlayers);
  }

  private void assembleIntegrals(Set<Player> participatingPlayers) {
    scoreBoard = new ScoreBoard<>(participatingPlayers);
    points = new HashSet<>(16);

  }
}
