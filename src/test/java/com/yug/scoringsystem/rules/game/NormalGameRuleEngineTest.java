package com.yug.scoringsystem.rules.game;


import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.game.*;
import com.yug.scoringsystem.rules.game.GameRuleEngineFactory;
import com.yug.scoringsystem.rules.game.IGameRuleEngine;
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
public class NormalGameRuleEngineTest {

  @Mock
  Game aGame;
  @Mock
  ScoreBoard aScoreBoard;

  @Before
  public void setUp() {
    when(aGame.getScoreBoard()).thenReturn(aScoreBoard);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(3L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player one")));

  }

  @Test
  public void shouldReturnUndecidedStateForOngoingGame() {
    //given
    IGameRuleEngine normalGameRuleEngine = GameRuleEngineFactory.getInstance().getRuleEngine(GameType.NORMAL);
    //when
    TangibleStatus fetchedStatus = normalGameRuleEngine.determineGameState(aGame);
    assertThat(fetchedStatus.getState()).isEqualTo(State.UNDECIDED);
  }

  @Test
  public void shouldReturnUndecidedStateForJustStartedGame() {
    //given
    IGameRuleEngine normalGameRuleEngine = GameRuleEngineFactory.getInstance().getRuleEngine(GameType.NORMAL);
    when(aScoreBoard.getLead()).thenReturn(0L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.empty());

    //when
    TangibleStatus fetchedStatus = normalGameRuleEngine.determineGameState(aGame);
    assertThat(fetchedStatus.getState()).isEqualTo(State.UNDECIDED);

  }

  @Test
  public void shouldReturnDeuceForEqualScores() {
    //given
    IGameRuleEngine normalGameRuleEngine = GameRuleEngineFactory.getInstance().getRuleEngine(GameType.NORMAL);
    when(aScoreBoard.getLead()).thenReturn(0L);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(5L);

    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player one")));
    //when
    TangibleStatus fetchedStatus = normalGameRuleEngine.determineGameState(aGame);
    assertThat(fetchedStatus.getState()).isEqualTo(State.DEUCE);

  }

  @Test
  public void shouldReturnAdvantageWhenPlayerHasIt() {
    //given
    IGameRuleEngine normalGameRuleEngine = GameRuleEngineFactory.getInstance().getRuleEngine(GameType.NORMAL);
    when(aScoreBoard.getLead()).thenReturn(1L);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(5L);

    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player one")));
    //when
    TangibleStatus fetchedStatus = normalGameRuleEngine.determineGameState(aGame);
    assertThat(fetchedStatus.getState()).isEqualTo(State.ADVANTAGE);

  }

  @Test
  public void shouldReturnWonWhenGameEnds() {
    //given
    IGameRuleEngine normalGameRuleEngine = GameRuleEngineFactory.getInstance().getRuleEngine(GameType.NORMAL);
    when(aScoreBoard.getLead()).thenReturn(2L);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(5L);

    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player one")));
    //when
    TangibleStatus fetchedStatus = normalGameRuleEngine.determineGameState(aGame);
    assertThat(fetchedStatus.getState()).isEqualTo(State.WON);

  }

}