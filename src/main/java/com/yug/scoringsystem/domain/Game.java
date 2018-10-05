package com.yug.scoringsystem.domain;


import java.util.HashSet;
import java.util.Set;

public class Game {
  private final String gameId;
  private Set<GamePoint> points;
  private Set<Player> participatingPlayers;

  public Game(String gameId, Player first, Player second) {
    this.gameId = gameId;
    initializeGame(first, second);
  }

  private void initializeGame(Player first, Player second) {
    points = new HashSet<>(16);
    participatingPlayers = new HashSet<>(16);
    participatingPlayers.add(first);
    participatingPlayers.add(second);
  }

  public String getGameId() {
    return gameId;
  }

  public Set<GamePoint> getPoints() {
    return points;
  }

  public Set<Player> getParticipatingPlayers() {
    return participatingPlayers;
  }

  public void addAPoint(GamePoint aPoint) {
    points.add(aPoint);
  }

}
