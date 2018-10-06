package com.yug.scoringsystem.domain.set;

import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.GameType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TennisSetTest {


  @Test
  public void shouldBeAbleToAddASetPoint() {
    //given
    Player firstPlayer = new Player("player one");
    Player secondPlayer = new Player("player Two");
    Game game = new Game("game1", firstPlayer, secondPlayer, GameType.NORMAL);
    TennisSet tennisSet = new TennisSet("game1", firstPlayer, secondPlayer);
    //when
    SetPoint aPoint = new SetPoint(game, firstPlayer);
    tennisSet.addAPoint(aPoint);
    //then
    assertThat(tennisSet.getPoints()).isNotNull().contains(aPoint);
  }

  @Test
  public void shouldBeAbleToGenerateSetName() {
    //given
    Player firstPlayer = new Player("playerOne");
    Player secondPlayer = new Player("playerTwo");
    TennisSet tennisSet = new TennisSet(firstPlayer, secondPlayer);
    //when
    //then
    assertThat(tennisSet.getSetId()).isEqualTo("playerOne VS playerTwo");
  }

}