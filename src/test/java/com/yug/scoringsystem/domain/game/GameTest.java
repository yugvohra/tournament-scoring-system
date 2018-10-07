package com.yug.scoringsystem.domain.game;

import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.TennisIntegral;
import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.game.GameState;
import com.yug.scoringsystem.domain.game.GameType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
  @Mock
  ScoreBoard aScoreBoard;
  @Mock
  TennisIntegral<GamePoint> tennisIntegral;
  private Game subject;


  @Before
  public void setUp() {
    subject = new Game(new Player("player 1"), new Player("player2"), GameType.NORMAL);
    Whitebox.setInternalState(subject, "tennisIntegral", tennisIntegral);
    when(tennisIntegral.getScoreBoard()).thenReturn(aScoreBoard);
  }

  @Test
  public void shouldReturnUndecidedStateForOngoingGame() {
    when(aScoreBoard.getPlayerScore(any())).thenReturn(3L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player one")));
    when(aScoreBoard.getLead()).thenReturn(3L);
    //given
    subject.addAPoint(new GamePoint(new Player("player one")));
    //when
    assertThat(subject.getState()).isEqualTo(GameState.UNDECIDED);
  }

  @Test
  public void shouldReturnUndecidedStateForJustStartedGame() {
    //given
    when(aScoreBoard.getLead()).thenReturn(0L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.empty());
    //when
    subject.addAPoint(new GamePoint(new Player("player one")));
    //then
    assertThat(subject.getState()).isEqualTo(GameState.UNDECIDED);
  }

  @Test
  public void shouldReturnDeuceForEqualScores() {
    //given
    when(aScoreBoard.getLead()).thenReturn(0L);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(4L);

    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player one")));
    //when
    subject.addAPoint(new GamePoint(new Player("player one")));
    //when
    assertThat(subject.getState()).isEqualTo(GameState.DEUCE);

  }

  @Test
  public void shouldReturnAdvantageWhenPlayerHasIt() {
    //given
    //the state should be deuce
    when(aScoreBoard.getLead()).thenReturn(0L);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(5L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player one")));
    subject.addAPoint(new GamePoint(new Player("player one")));

    when(aScoreBoard.getLead()).thenReturn(1L);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(5L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player one")));
    //when
    subject.addAPoint(new GamePoint(new Player("player one")));
    //then
    assertThat(subject.getState()).isEqualTo(GameState.ADVANTAGE);
  }

  @Test
  public void shouldReturnWonWhenGameEnds() {
    //given
    when(aScoreBoard.getLead()).thenReturn(2L);
    when(aScoreBoard.getPlayerScore(any())).thenReturn(5L);

    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player("player one")));
    //when
    subject.addAPoint(new GamePoint(new Player("player one")));
    //then
    assertThat(subject.getState()).isEqualTo(GameState.WON);
  }

  @Test
  public void shouldBeAbleToAddAPoint() {
    //given
    Player firstPlayer = new Player("player one");
    Player secondPlayer = new Player("player Two");
    Game game = new Game("game1", firstPlayer, secondPlayer, GameType.NORMAL);
    //when
    GamePoint aPoint = new GamePoint(firstPlayer);
    game.addAPoint(aPoint);
    //then
    assertThat(game.getPoints()).isNotNull().contains(aPoint);
  }

  @Test
  public void shouldBeAbleToGenerateGameName() {
    //given
    Player firstPlayer = new Player("playerOne");
    Player secondPlayer = new Player("playerTwo");
    Game game = new Game(firstPlayer, secondPlayer, GameType.NORMAL);
    //when
    //then
    assertThat(game.getGameId()).isEqualTo("playerOne VS playerTwo");
  }


}
