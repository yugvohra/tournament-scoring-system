package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
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

import static com.yug.scoringsystem.domain.game.GameState.UNDECIDED_TIEBREAKER;
import static com.yug.scoringsystem.domain.game.GameState.WON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TieBreakerStringConverterTest {
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
    when(aTangibleStatus.getState()).thenReturn(UNDECIDED_TIEBREAKER);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(2L);
    when(aScoreBoard.getParticipatingPlayers()).thenReturn(participatingPlayers);

    //when
    String message = TieBreakerStringConverter.getInstance().fetchStringMessageFrom(aTangibleStatus);

    assertThat(message).contains("player 2 2").contains("player 1 2");

  }

  @Test
  public void shouldReturnCorrectMessageForfinishedGame() {
    //given
    when(aTangibleStatus.getState()).thenReturn(WON);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player 1")));

    //when
    String message = TieBreakerStringConverter.getInstance().fetchStringMessageFrom(aTangibleStatus);

    assertThat(message).isEqualTo("game won by player 1");

  }


}