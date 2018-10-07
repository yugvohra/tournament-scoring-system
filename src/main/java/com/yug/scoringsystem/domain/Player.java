package com.yug.scoringsystem.domain;

import java.util.Objects;

public class Player {
  private final String Name;

  public Player(String name) {
    Name = name;
  }

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

  public String getName() {
    return Name;
  }
}
