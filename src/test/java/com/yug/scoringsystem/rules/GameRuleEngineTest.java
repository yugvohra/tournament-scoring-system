package com.yug.scoringsystem.rules;


import com.yug.scoringsystem.domain.*;
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
public class GameRuleEngineTest {

  @Mock
  Game aGame;
  @Mock
  GameScore aGameScore;

  @Before
  public void setUp() {
    when(aGame.getGameScore()).thenReturn(aGameScore);
    when(aGameScore.getPlayerScore(any())).thenReturn(3L);
    when(aGameScore.getLeadPlayer()).thenReturn(Optional.of(new Player("player one", "1")));

  }

  @Test
  public void shouldReturnUndecidedStateForOngoingGame() {
    //given
    GameRuleEngine gameRuleEngine = GameRuleEngine.getInstance();
    //when
    GameStatus fetchedStatus = gameRuleEngine.determineGameState(aGame);
    assertThat(fetchedStatus.getGameState()).isEqualTo(GameState.UNDECIDED);
    assertThat(fetchedStatus.getGameScore()).isEqualTo(GameState.UNDECIDED);

  }

  @Test
  public void shouldReturnUndecidedStateForJustStartedGame() {
    //given
    GameRuleEngine gameRuleEngine = GameRuleEngine.getInstance();
    when(aGameScore.getLead()).thenReturn(0L);
    when(aGameScore.getLeadPlayer()).thenReturn(Optional.empty());

    //when
    GameStatus fetchedState = gameRuleEngine.determineGameState(aGame);
    assertThat(fetchedState).isEqualTo(GameState.UNDECIDED);

  }

  @Test
  public void shouldReturnDeuceForEqualScores() {
    //given
    GameRuleEngine gameRuleEngine = GameRuleEngine.getInstance();
    when(aGameScore.getLead()).thenReturn(0L);
    when(aGameScore.getPlayerScore(any())).thenReturn(5L);

    when(aGameScore.getLeadPlayer()).thenReturn(Optional.of(new Player("player one", "1")));
    //when
    GameStatus fetchedState = gameRuleEngine.determineGameState(aGame);
    assertThat(fetchedState).isEqualTo(GameState.DEUCE);

  }

  @Test
  public void shouldReturnAdvantageWhenPlayerHasIt() {
    //given
    GameRuleEngine gameRuleEngine = GameRuleEngine.getInstance();
    when(aGameScore.getLead()).thenReturn(1L);
    when(aGameScore.getPlayerScore(any())).thenReturn(5L);

    when(aGameScore.getLeadPlayer()).thenReturn(Optional.of(new Player("player one", "1")));
    //when
    GameStatus fetchedState = gameRuleEngine.determineGameState(aGame);
    assertThat(fetchedState).isEqualTo(GameState.ADVANTAGE);

  }

  @Test
  public void shouldReturnWonWhenGameEnds() {
    //given
    GameRuleEngine gameRuleEngine = GameRuleEngine.getInstance();
    when(aGameScore.getLead()).thenReturn(2L);
    when(aGameScore.getPlayerScore(any())).thenReturn(5L);

    when(aGameScore.getLeadPlayer()).thenReturn(Optional.of(new Player("player one", "1")));
    //when
    GameStatus fetchedState = gameRuleEngine.determineGameState(aGame);
    assertThat(fetchedState).isEqualTo(GameState.WON);

  }

}