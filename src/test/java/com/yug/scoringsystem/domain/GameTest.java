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

  @Test
  public void shouldReturnEmptyLeadPlayer_At_StartOfGame() {
    //given
    Player firstPlayer = new Player("player one", "1");
    Player secondPlayer = new Player("player Two", "2");
    Game game = new Game("game1", firstPlayer, secondPlayer);
    //when
    //then
    assertThat(game.getLeadingPlayer()).isEmpty();
  }

  @Test
  public void shouldBeAbleToReturnCorrectLeadPlayer() {
    //given
    Player firstPlayer = new Player("player one", "1");
    Player secondPlayer = new Player("player Two", "2");
    Game game = new Game("game1", firstPlayer, secondPlayer);
    //when
    GamePoint aPoint = new GamePoint(firstPlayer);
    game.addAPoint(aPoint);
    //then
    assertThat(game.getLeadingPlayer()).isNotEmpty();
    assertThat(game.getLeadingPlayer().get()).isEqualTo(firstPlayer);
  }

  @Test
  public void shouldReturnCorrectScore() {
    //given
    Player firstPlayer = new Player("player one", "1");
    Player secondPlayer = new Player("player Two", "2");
    Game game = new Game("game1", firstPlayer, secondPlayer);
    //when
    game.addAPoint(new GamePoint(firstPlayer));
    game.addAPoint(new GamePoint(firstPlayer));
    game.addAPoint(new GamePoint(firstPlayer));
    game.addAPoint(new GamePoint(secondPlayer));
    //then
    assertThat(game.getPlayerScore(firstPlayer)).isEqualTo(3);
    assertThat(game.getPlayerScore(secondPlayer)).isEqualTo(1);
    assertThat(game.getLeadingPlayer().get()).isEqualTo(firstPlayer);
  }

  @Test
  public void shouldReturn_ZeroLead_AtGameStart() {
    //given
    Player firstPlayer = new Player("player one", "1");
    Player secondPlayer = new Player("player Two", "2");
    Game game = new Game("game1", firstPlayer, secondPlayer);
    //when
    //then
    assertThat(game.getLead()).isEqualTo(0L);
  }

  @Test
  public void shouldReturnCorrectLead() {
    //given
    Player firstPlayer = new Player("player one", "1");
    Player secondPlayer = new Player("player Two", "2");
    Game game = new Game("game1", firstPlayer, secondPlayer);
    //when
    game.addAPoint(new GamePoint(firstPlayer));
    game.addAPoint(new GamePoint(firstPlayer));
    game.addAPoint(new GamePoint(firstPlayer));
    game.addAPoint(new GamePoint(secondPlayer));
    //then
    assertThat(game.getLead()).isEqualTo(2L);
  }

  @Test
  public void shouldSwapAndReturnCorrectLead() {
    //given
    Player firstPlayer = new Player("player one", "1");
    Player secondPlayer = new Player("player Two", "2");
    Game game = new Game("game1", firstPlayer, secondPlayer);
    //when
    game.addAPoint(new GamePoint(firstPlayer));
    game.addAPoint(new GamePoint(firstPlayer));
    game.addAPoint(new GamePoint(secondPlayer));
    game.addAPoint(new GamePoint(secondPlayer));
    game.addAPoint(new GamePoint(secondPlayer));
    //then
    assertThat(game.getLead()).isEqualTo(1L);
  }
}
