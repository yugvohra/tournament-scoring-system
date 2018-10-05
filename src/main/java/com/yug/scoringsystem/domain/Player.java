package com.yug.scoringsystem.domain;

public class Player {
  private final String Name;
  private final String playerId;

  public Player(String name, String playerId) {
    Name = name;
    this.playerId = playerId;
  }

  public String getName() {
    return Name;
  }

  public String getPlayerId() {
    return playerId;
  }
}
