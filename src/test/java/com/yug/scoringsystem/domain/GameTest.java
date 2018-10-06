package com.yug.scoringsystem.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {

  @Test
  public void shouldBeAbleToAddAPoint() {
    //given
    Player firstPlayer = new Player("player one", "1");
    Player secondPlayer = new Player("player Two", "2");
    Game game = new Game("game1", firstPlayer, secondPlayer);
    //when
    GamePoint aPoint = new GamePoint(firstPlayer);
    game.addAPoint(aPoint);
    //then
    assertThat(game.getPoints()).isNotNull().contains(aPoint);
  }

  @Test
  public void shouldBeAbleToGenerateGameName() {
    //given
    Player firstPlayer = new Player("playerOne", "1");
    Player secondPlayer = new Player("playerTwo", "2");
    Game game = new Game(firstPlayer, secondPlayer);
    //when
    //then
    assertThat(game.getGameId()).isEqualTo("playerOne VS playerTwo");
  }

}
