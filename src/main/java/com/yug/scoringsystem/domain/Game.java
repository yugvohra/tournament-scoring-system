package com.yug.scoringsystem.domain;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
  private final String gameId;
  private List<Player> participatingPlayers;
  private Set<GamePoint> points;
  private GameScore gameScore;

  Game(String gameId, Player playerOne, Player playerTwo) {
    this.gameId = gameId;
    initializeGame(playerOne, playerTwo);
  }

  Game(Player playerOne, Player playerTwo) {
    this(String.format("%s VS %s", playerOne.getName(), playerTwo.getName()), playerOne, playerTwo);
  }


  private void initializeGame(Player playerOne, Player playerTwo) {
    points = new HashSet<>(16);
    participatingPlayers = new ArrayList<>();
    participatingPlayers.add(playerOne);
    participatingPlayers.add(playerTwo);
    gameScore = new GameScore(participatingPlayers);
  }

  public List<Player> getParticipatingPlayers() {
    return this.participatingPlayers;
  }

  String getGameId() {
    return gameId;
  }

  Set<GamePoint> getPoints() {
    return points;
  }

  void addAPoint(GamePoint aPoint) {
    points.add(aPoint);
    this.getGameScore().addPoint(aPoint);
  }

  public GameScore getGameScore() {
    return gameScore;
  }

}
