package com.yug.scoringsystem.domain;

import java.util.Objects;

public class Player {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return Objects.equals(Name, player.Name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Name);
  }

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
