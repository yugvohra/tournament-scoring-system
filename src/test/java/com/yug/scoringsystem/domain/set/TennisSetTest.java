package com.yug.scoringsystem.domain.set;

import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.TennisIntegral;
import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.game.GameType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static com.yug.scoringsystem.domain.set.SetState.UNDECIDED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TennisSetTest {
  @Mock
  ScoreBoard aScoreBoard;
  @Mock
  TennisIntegral<GamePoint> tennisIntegral;
  private TennisSet subject;
  private Game game;


  @Before
  public void setUp() {
    game = new Game(new Player("player 1"), new Player("player2"), GameType.NORMAL);
    subject = new TennisSet(new Player("player 1"), new Player("player2"));
    Whitebox.setInternalState(subject, "tennisIntegral", tennisIntegral);
    when(tennisIntegral.getScoreBoard()).thenReturn(aScoreBoard);
  }

  @Test
  public void shouldReturnUndecidedStateForJustStartedSet() {
    //given
    when(aScoreBoard.getLead()).thenReturn(0L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.empty());
    //when
    assertThat(subject.getState()).isEqualTo(UNDECIDED);
  }

  @Test
  public void shouldReturnUndecidedStateForOngoingSet() {
    //given
    when(aScoreBoard.getLead()).thenReturn(3L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player(" player 1")));
    when(aScoreBoard.getPlayerScore(any())).thenReturn(5L);

    //when
    subject.addAPoint(new SetPoint(game, new Player("player one")));
    //then
    assertThat(subject.getState()).isEqualTo(SetState.UNDECIDED);
  }

  @Test
  public void shouldReturnWonForStraightWin() {
    //given
    when(aScoreBoard.getLead()).thenReturn(3L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player(" player 1")));
    when(aScoreBoard.getPlayerScore(any())).thenReturn(6L);

    //when
    subject.addAPoint(new SetPoint(game, new Player("player one")));
    //then
    assertThat(subject.getState()).isEqualTo(SetState.WON);
  }

  @Test
  public void shouldtReturnWinnForTrailingGame() {
    //given
    when(aScoreBoard.getLead()).thenReturn(2L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player(" player 1")));
    when(aScoreBoard.getPlayerScore(any())).thenReturn(7L);

    //when
    //when
    subject.addAPoint(new SetPoint(game, new Player("player one")));
    //then
    assertThat(subject.getState()).isEqualTo(SetState.WON);
  }


  @Test
  public void shouldReturnTieStatus() {
    //given
    when(aScoreBoard.getLead()).thenReturn(0L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player(" player 1")));
    when(aScoreBoard.getPlayerScore(any())).thenReturn(6L);
    //when
    subject.addAPoint(new SetPoint(game, new Player("player one")));
    //then
    assertThat(subject.getState()).isEqualTo(SetState.TIE);
  }

  @Test
  public void shouldReturnUndecidedForLeadLessThan1() {
    //given
    when(aScoreBoard.getLead()).thenReturn(1L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player(" player 1")));
    when(aScoreBoard.getPlayerScore(any())).thenReturn(6L);

    //when
    //when
    subject.addAPoint(new SetPoint(game, new Player("player one")));
    //then
    assertThat(subject.getState()).isEqualTo(SetState.UNDECIDED);
  }

  @Test
  public void shouldReturWonStateWhenTrailingGameEnds() {
    //given state is TIE
    when(aScoreBoard.getLead()).thenReturn(0L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player(" player 1")));
    when(aScoreBoard.getPlayerScore(any())).thenReturn(6L);
    //when
    subject.addAPoint(new SetPoint(game, new Player("player one")));
    //then
    when(aScoreBoard.getLead()).thenReturn(1L);
    when(aScoreBoard.getLeadPlayer()).thenReturn(Optional.of(new Player(" player 1")));
    when(aScoreBoard.getPlayerScore(any())).thenReturn(7L);

    //when
    subject.addAPoint(new SetPoint(game, new Player("player one")));
    //then
    assertThat(subject.getState()).isEqualTo(SetState.WON);
  }


  @Test
  public void shouldBeAbleToAddASetPoint() {
    //given
    Player firstPlayer = new Player("player one");
    Player secondPlayer = new Player("player Two");
    Game game = new Game("game1", firstPlayer, secondPlayer, GameType.NORMAL);
    TennisSet tennisSet = new TennisSet("game1", firstPlayer, secondPlayer);
    //when
    SetPoint aPoint = new SetPoint(game, firstPlayer);
    tennisSet.addAPoint(aPoint);
    //then
    assertThat(tennisSet.getPoints()).isNotNull().contains(aPoint);
  }

  @Test
  public void shouldBeAbleToGenerateSetName() {
    //given
    Player firstPlayer = new Player("playerOne");
    Player secondPlayer = new Player("playerTwo");
    TennisSet tennisSet = new TennisSet(firstPlayer, secondPlayer);
    //when
    //then
    assertThat(tennisSet.getSetId()).isEqualTo("playerOne VS playerTwo");
  }

}