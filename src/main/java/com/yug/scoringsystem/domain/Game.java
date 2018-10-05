package com.yug.scoringsystem.domain;


import java.util.HashSet;
import java.util.Set;

public class Game {
  private final String gameId;
  private Set<GamePoint> points;
  private Set<Player> participatingPlayers;

  public Game(String gameId, Player playerOne, Player playerTwo) {
    this.gameId = gameId;
    initializeGame(playerOne, playerTwo);
  }

  public Game(Player playerOne, Player playerTwo) {
    this(String.format("%s VS %s", playerOne.getName(), playerTwo.getName()), playerOne, playerTwo);
  }

  private void initializeGame(Player playerOne, Player playerTwo) {
    points = new HashSet<>(16);
    participatingPlayers = new HashSet<>(16);
    participatingPlayers.add(playerOne);
    participatingPlayers.add(playerTwo);
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
