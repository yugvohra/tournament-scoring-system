package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.GameScore;
import com.yug.scoringsystem.domain.GameState;
import com.yug.scoringsystem.domain.GameStatus;
import com.yug.scoringsystem.domain.Player;
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
public class GameStatusToStringMessageConverterTest {

  @Mock
  private GameScore aGameScore;
  @Mock
  private GameStatus aGameStatus;

  @Before
  public void setUp() {
    when(aGameStatus.getGameScore()).thenReturn(aGameScore);
  }

  @Test
  public void shouldReturnCorrectMessageForUndecidedGame() {
    //given
    Set<Player> participatingPlayers = new HashSet(Arrays.asList(new Player("player 1", "1"), new Player("player 2", "2")));
    when(aGameStatus.getGameState()).thenReturn(GameState.UNDECIDED);
    when(aGameScore.getPlayerScore(any())).thenReturn(2L);
    when(aGameScore.getParticipatingPlayers()).thenReturn(participatingPlayers);

    //when
    String message = GameStatusToStringMessageConverter.getInstance().fetchStringMessageFrom(aGameStatus);

    assertThat(message).contains("player 2 30").contains("player 1 30");

  }

  @Test
  public void shouldReturnCorrectMessageForDeuce() {
    //given
    Set<Player> participatingPlayers = new HashSet(Arrays.asList(new Player("player 1", "1"), new Player("player 2", "2")));
    when(aGameStatus.getGameState()).thenReturn(GameState.DEUCE);
    when(aGameScore.getPlayerScore(any())).thenReturn(3L);
    when(aGameScore.getParticipatingPlayers()).thenReturn(participatingPlayers);

    //when
    String message = GameStatusToStringMessageConverter.getInstance().fetchStringMessageFrom(aGameStatus);

    assertThat(message).contains("Deuce").contains("player 2 40").contains("player 1 40");;

  }

  @Test
  public void shouldReturnCorrectMessageForAdvantage() {
    //given
    when(aGameStatus.getGameState()).thenReturn(GameState.ADVANTAGE);
    when(aGameScore.getLeadPlayer()).thenReturn(Optional.of(new Player("player 1","1")));

    //when
    String message = GameStatusToStringMessageConverter.getInstance().fetchStringMessageFrom(aGameStatus);

    assertThat(message).isEqualTo("Advantage to player 1");

  }

  @Test
  public void shouldReturnCorrectMessageForfinishedGame() {
    //given
    when(aGameStatus.getGameState()).thenReturn(GameState.WON);
    when(aGameScore.getLeadPlayer()).thenReturn(Optional.of(new Player("player 1","1")));

    //when
    String message = GameStatusToStringMessageConverter.getInstance().fetchStringMessageFrom(aGameStatus);

    assertThat(message).isEqualTo("Game won by player 1");

  }


}