package com.yug.scoringsystem.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {

  @Test
  public void shouldBeAbleToAddAPoint() {
    //given
    Player firstPlayer = new Player();
    Player secondPlayer = new Player();
    Game game = new Game("game1", firstPlayer, secondPlayer);
    //when
    GamePoint aPoint = new GamePoint(game, firstPlayer);
    game.addAPoint(aPoint);
    //then
    assertThat(game.getPoints()).isNotNull().contains(aPoint);
  }
}
