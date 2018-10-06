package com.yug.scoringsystem.domain;

import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.game.GameType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {

  @Test
  public void shouldBeAbleToAddAPoint() {
    //given
    Player firstPlayer = new Player("player one");
    Player secondPlayer = new Player("player Two");
    Game game = new Game("game1", firstPlayer, secondPlayer, GameType.NORMAL);
    //when
    GamePoint aPoint = new GamePoint(firstPlayer);
    game.addAPoint(aPoint);
    //then
    assertThat(game.getPoints()).isNotNull().contains(aPoint);
  }

  @Test
  public void shouldBeAbleToGenerateGameName() {
    //given
    Player firstPlayer = new Player("playerOne");
    Player secondPlayer = new Player("playerTwo");
    Game game = new Game(firstPlayer, secondPlayer, GameType.NORMAL);
    //when
    //then
    assertThat(game.getGameId()).isEqualTo("playerOne VS playerTwo");
  }

}
