package com.yug.scoringsystem.rules;


import com.yug.scoringsystem.domain.Game;
import com.yug.scoringsystem.domain.GameState;
import com.yug.scoringsystem.domain.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameRuleEngineTest {

  @Mock
  Game aGame;

  @Test
  public void shouldReturnUndecidedStateForOngoingGame() {
    //given
    GameRuleEngine gameRuleEngine = GameRuleEngine.getInstance();
    when(aGame.getLead()).thenReturn(1L);
    when(aGame.getLeadingPlayer()).thenReturn(Optional.of(new Player("player one","1")));
    //when
    GameState fetchedState = gameRuleEngine.determineGameState(aGame);
    assertThat(fetchedState).isEqualTo(GameState.UNDECIDED);

  }

  @Test
  public void shouldReturnUndecidedStateForJustStartedGame() {
    //given
    GameRuleEngine gameRuleEngine = GameRuleEngine.getInstance();
    when(aGame.getLead()).thenReturn(0L);
    when(aGame.getLeadingPlayer()).thenReturn(Optional.empty());

    //when
    GameState fetchedState = gameRuleEngine.determineGameState(aGame);
    assertThat(fetchedState).isEqualTo(GameState.UNDECIDED);

  }

  @Test
  public void shouldReturnDeuceForEqualScores() {
    //given
    GameRuleEngine gameRuleEngine = GameRuleEngine.getInstance();
    when(aGame.getLead()).thenReturn(0L);
    when(aGame.getLeadingPlayer()).thenReturn(Optional.of(new Player("player one", "1")));
    //when
    GameState fetchedState = gameRuleEngine.determineGameState(aGame);
    assertThat(fetchedState).isEqualTo(GameState.DEUCE);

  }

}