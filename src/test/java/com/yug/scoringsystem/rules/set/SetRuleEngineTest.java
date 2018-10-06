package com.yug.scoringsystem.rules.set;

import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.game.State;
import com.yug.scoringsystem.domain.game.TangibleStatus;
import com.yug.scoringsystem.domain.set.TennisSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SetRuleEngineTest {
  @Mock
  TennisSet aSet;
  @Mock
  ScoreBoard aScoreBoard;

  @Before
  public void setUp() {
    when(aSet.getScoreBoard()).thenReturn(aScoreBoard);

  }

  @Test
  public void shouldReturnUndecidedStateForJustStartedSet() {
    //given
    SetRuleEngine setRuleEngine = SetRuleEngine.getInstance();
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.empty());
    //when
    TangibleStatus fetchedStatus = setRuleEngine.determineSetState(aSet);
    assertThat(fetchedStatus.getState()).isEqualTo(State.UNDECIDED);
  }

  @Test
  public void shouldReturnUndecidedStateForOngoingSet() {
    //given
    SetRuleEngine setRuleEngine = SetRuleEngine.getInstance();
    when(aScoreBoard.getLead()).thenReturn(3L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player(" player 1")));
    when(aScoreBoard.getPlayerScore(any())).thenReturn(6L);

    //when
    TangibleStatus fetchedStatus = setRuleEngine.determineSetState(aSet);
    assertThat(fetchedStatus.getState()).isEqualTo(State.UNDECIDED);

  }

  @Test
  public void shouldReturnUndecidedForLeadLessThan1() {
    //given
    SetRuleEngine setRuleEngine = SetRuleEngine.getInstance();
    when(aScoreBoard.getLead()).thenReturn(1L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player(" player 1")));
    when(aScoreBoard.getPlayerScore(any())).thenReturn(6L);

    //when
    TangibleStatus fetchedStatus = setRuleEngine.determineSetState(aSet);
    assertThat(fetchedStatus.getState()).isEqualTo(State.UNDECIDED);

  }

  @Test
  public void shouldReturWonStateWhenGameEnds() {
    //given
    SetRuleEngine setRuleEngine = SetRuleEngine.getInstance();
    when(aScoreBoard.getLead()).thenReturn(2L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player(" player 1")));
    when(aScoreBoard.getPlayerScore(any())).thenReturn(8L);

    //when
    TangibleStatus fetchedStatus = setRuleEngine.determineSetState(aSet);
    assertThat(fetchedStatus.getState()).isEqualTo(State.WON);

  }

}