package com.yug.scoringsystem.domain;

import com.yug.scoringsystem.domain.game.GamePoint;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ScoreBoardTest {


  private Set<Player> participatingPlayers;
  private Player firstPlayer;
  private Player secondPlayer;

  @Before
  public void setUp() {
    firstPlayer = new Player("player one");
    secondPlayer = new Player("player Two");
    participatingPlayers = new HashSet(Arrays.asList(firstPlayer, secondPlayer));
  }

  @Test
  public void shouldReturnEmptyLeadPlayer_At_StartOfGame() {
    //given

    ScoreBoard scoreBoard = new ScoreBoard(participatingPlayers);

    //then
    assertThat(scoreBoard.getLeadPlayer()).isEmpty();
  }

  @Test
  public void shouldBeAbleToReturnCorrectLeadPlayer() {
    //given
    ScoreBoard scoreBoard = new ScoreBoard(participatingPlayers);
    //when
    GamePoint aPoint = new GamePoint(firstPlayer);
    scoreBoard.addPoint(aPoint);
    //then
    assertThat(scoreBoard.getLeadPlayer()).isNotEmpty();
    assertThat(scoreBoard.getLeadPlayer().get()).isEqualTo(firstPlayer);
  }

  @Test
  public void shouldReturnCorrectScore() {
    //given
    ScoreBoard scoreBoard = new ScoreBoard(participatingPlayers);
    //when
    scoreBoard.addPoint(new GamePoint(firstPlayer));
    scoreBoard.addPoint(new GamePoint(firstPlayer));
    scoreBoard.addPoint(new GamePoint(firstPlayer));
    scoreBoard.addPoint(new GamePoint(secondPlayer));
    //then
    assertThat(scoreBoard.getPlayerScore(firstPlayer)).isEqualTo(3);
    assertThat(scoreBoard.getPlayerScore(secondPlayer)).isEqualTo(1);
    assertThat(scoreBoard.getLeadPlayer().get()).isEqualTo(firstPlayer);
  }

  @Test
  public void shouldReturn_ZeroLead_AtGameStart() {
    //given
    ScoreBoard scoreBoard = new ScoreBoard(participatingPlayers);
    //when
    //then
    assertThat(scoreBoard.getLead()).isEqualTo(0L);
  }

  @Test
  public void shouldReturnCorrectLead() {
    //given
    ScoreBoard scoreBoard = new ScoreBoard(participatingPlayers);

    //when
    scoreBoard.addPoint(new GamePoint(firstPlayer));
    scoreBoard.addPoint(new GamePoint(firstPlayer));
    scoreBoard.addPoint(new GamePoint(firstPlayer));
    scoreBoard.addPoint(new GamePoint(secondPlayer));
    //then
    assertThat(scoreBoard.getLead()).isEqualTo(2L);
  }

  @Test
  public void shouldSwapAndReturnCorrectLead() {
    //given
    ScoreBoard scoreBoard = new ScoreBoard(participatingPlayers);

    //when
    scoreBoard.addPoint(new GamePoint(firstPlayer));
    scoreBoard.addPoint(new GamePoint(firstPlayer));
    scoreBoard.addPoint(new GamePoint(secondPlayer));
    scoreBoard.addPoint(new GamePoint(secondPlayer));
    scoreBoard.addPoint(new GamePoint(secondPlayer));
    //then
    assertThat(scoreBoard.getLead()).isEqualTo(1L);
  }


}
