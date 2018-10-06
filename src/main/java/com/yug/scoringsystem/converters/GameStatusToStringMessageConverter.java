package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.GameStatus;

public class GameStatusToStringMessageConverter {
  private static GameStatusToStringMessageConverter INSTANCE = new GameStatusToStringMessageConverter();

  private GameStatusToStringMessageConverter() {

  }

  public static GameStatusToStringMessageConverter getInstance() {
    return INSTANCE;
  }

  public String fetchStringMessageFrom(GameStatus aGameStatus) {
    return null;
  }
}
