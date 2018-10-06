package com.yug.scoringsystem.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GameScoreTest {


  private List<Player> participatingPlayers;
  private Player firstPlayer;
  private Player secondPlayer;

  @Before
  public void setUp()
  {
    firstPlayer = new Player("player one", "1");
    secondPlayer = new Player("player Two", "2");
    participatingPlayers = Arrays.asList(firstPlayer, secondPlayer);
  }
  
  @Test
  public void shouldReturnEmptyLeadPlayer_At_StartOfGame() {
    //given

    GameScore gameScore = new GameScore(participatingPlayers);
    
    //then
    assertThat(gameScore.getLeadPlayer()).isEmpty();
  }

  @Test
  public void shouldBeAbleToReturnCorrectLeadPlayer() {
    //given
    GameScore gameScore=new GameScore(participatingPlayers);
    //when
    GamePoint aPoint = new GamePoint(firstPlayer);
    gameScore.addPoint(aPoint);
    //then
    assertThat(gameScore.getLeadPlayer()).isNotEmpty();
    assertThat(gameScore.getLeadPlayer().get()).isEqualTo(firstPlayer);
  }

  @Test
  public void shouldReturnCorrectScore() {
    //given
    GameScore gameScore=new GameScore(participatingPlayers);
    //when
    gameScore.addPoint(new GamePoint(firstPlayer));
    gameScore.addPoint(new GamePoint(firstPlayer));
    gameScore.addPoint(new GamePoint(firstPlayer));
    gameScore.addPoint(new GamePoint(secondPlayer));
    //then
    assertThat(gameScore.getPlayerScore(firstPlayer)).isEqualTo(3);
    assertThat(gameScore.getPlayerScore(secondPlayer)).isEqualTo(1);
    assertThat(gameScore.getLeadPlayer().get()).isEqualTo(firstPlayer);
  }

  @Test
  public void shouldReturn_ZeroLead_AtGameStart() {
    //given
    GameScore gameScore=new GameScore(participatingPlayers);
    //when
    //then
    assertThat(gameScore.getLead()).isEqualTo(0L);
  }

  @Test
  public void shouldReturnCorrectLead() {
    //given
    GameScore gameScore=new GameScore(participatingPlayers);

    //when
    gameScore.addPoint(new GamePoint(firstPlayer));
    gameScore.addPoint(new GamePoint(firstPlayer));
    gameScore.addPoint(new GamePoint(firstPlayer));
    gameScore.addPoint(new GamePoint(secondPlayer));
    //then
    assertThat(gameScore.getLead()).isEqualTo(2L);
  }

  @Test
  public void shouldSwapAndReturnCorrectLead() {
    //given
    GameScore gameScore=new GameScore(participatingPlayers);

    //when
    gameScore.addPoint(new GamePoint(firstPlayer));
    gameScore.addPoint(new GamePoint(firstPlayer));
    gameScore.addPoint(new GamePoint(secondPlayer));
    gameScore.addPoint(new GamePoint(secondPlayer));
    gameScore.addPoint(new GamePoint(secondPlayer));
    //then
    assertThat(gameScore.getLead()).isEqualTo(1L);
  }


}
