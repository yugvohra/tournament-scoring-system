package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.game.State;
import com.yug.scoringsystem.domain.game.TangibleStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTangibleStatusToStringConverterTest {

  @Mock
  private ScoreBoard aScoreBoard;
  @Mock
  private TangibleStatus aTangibleStatus;

  @Before
  public void setUp() {
    when(aTangibleStatus.getScoreBoard()).thenReturn(aScoreBoard);
  }

  @Test
  public void shouldReturnCorrectMessageForUndecidedGame() {
    //given
    Set<Player> participatingPlayers = new HashSet(Arrays.asList(new Player("player 1"), new Player("player 2")));
    when(aTangibleStatus.getState()).thenReturn(State.UNDECIDED);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(2L);
    when(aScoreBoard.getParticipatingPlayers()).thenReturn(participatingPlayers);

    //when
    String message = ClockGameStringConverter.getInstance().fetchStringMessageFrom(aTangibleStatus);

    assertThat(message).contains("player 2 30").contains("player 1 30");

  }

  @Test
  public void shouldReturnCorrectMessageForDeuce() {
    //given
    Set<Player> participatingPlayers = new HashSet(Arrays.asList(new Player("player 1"), new Player("player 2")));
    when(aTangibleStatus.getState()).thenReturn(State.DEUCE);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(3L);
    when(aScoreBoard.getParticipatingPlayers()).thenReturn(participatingPlayers);

    //when
    String message = ClockGameStringConverter.getInstance().fetchStringMessageFrom(aTangibleStatus);

    assertThat(message).contains("Deuce").contains("player 2 40").contains("player 1 40");
    ;

  }

  @Test
  public void shouldReturnCorrectMessageForAdvantage() {
    //given
    when(aTangibleStatus.getState()).thenReturn(State.ADVANTAGE);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player 1")));

    //when
    String message = ClockGameStringConverter.getInstance().fetchStringMessageFrom(aTangibleStatus);

    assertThat(message).isEqualTo("Advantage to player 1");

  }

  @Test
  public void shouldReturnCorrectMessageForfinishedGame() {
    //given
    when(aTangibleStatus.getState()).thenReturn(State.WON);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player 1")));

    //when
    String message = ClockGameStringConverter.getInstance().fetchStringMessageFrom(aTangibleStatus);

    assertThat(message).isEqualTo("Game won by player 1");

  }


}