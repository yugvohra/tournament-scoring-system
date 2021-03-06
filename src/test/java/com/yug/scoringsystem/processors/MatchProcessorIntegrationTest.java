package com.yug.scoringsystem.processors;

import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.set.SetPoint;
import com.yug.scoringsystem.helpers.loggers.MatchStatusConsoleLogger;
import org.junit.Test;

import static com.yug.scoringsystem.domain.game.GameState.DEUCE;
import static com.yug.scoringsystem.domain.set.SetState.UNDECIDED;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MatchProcessorIntegrationTest {

  @Test
  public void shouldSetOutcomeAsUndecided() {
    String PLAYER_ONE = "player 1";
    String PLAYER_TWO = "player 2";
    MatchProcessor matchProcessor = new MatchProcessor(PLAYER_ONE, PLAYER_TWO, new MatchStatusConsoleLogger());
    matchProcessor.initializeMatch();

    matchProcessor.pointWonBy(PLAYER_ONE);
    matchProcessor.pointWonBy(PLAYER_ONE);
    matchProcessor.pointWonBy(PLAYER_ONE);
    matchProcessor.pointWonBy(PLAYER_ONE);
    matchProcessor.pointWonBy(PLAYER_ONE);

    ScoreBoard<SetPoint> setScoreBoard = matchProcessor.getMatch().getTennisSet().getScoreBoard();
    ScoreBoard<GamePoint> gameScoreBoard = matchProcessor.getMatch().getTennisSet().getCurrentGame().getScoreBoard();

    assertThat(setScoreBoard.getPlayerScore(setScoreBoard.getLeadPlayer().get())).isEqualTo(1L);
    assertThat(matchProcessor.getMatch().getTennisSet().getSetState()).isEqualTo(UNDECIDED);
    assertThat(gameScoreBoard.getPlayerScore(gameScoreBoard.getLeadPlayer().get())).isEqualTo(1L);
  }


  @Test
  public void shouldNotConcludeGame() {
    String PLAYER_ONE = "player 1";
    String PLAYER_TWO = "player 2";
    MatchProcessor matchProcessor = new MatchProcessor(PLAYER_ONE, PLAYER_TWO, new MatchStatusConsoleLogger());
    matchProcessor.initializeMatch();

    matchProcessor.pointWonBy(PLAYER_ONE);
    matchProcessor.pointWonBy(PLAYER_ONE);
    matchProcessor.pointWonBy(PLAYER_ONE);
    matchProcessor.pointWonBy(PLAYER_TWO);
    matchProcessor.pointWonBy(PLAYER_TWO);
    matchProcessor.pointWonBy(PLAYER_TWO);

    ScoreBoard<SetPoint> setScoreBoard = matchProcessor.getMatch().getTennisSet().getScoreBoard();
    ScoreBoard<GamePoint> gameScoreBoard = matchProcessor.getMatch().getTennisSet().getCurrentGame().getScoreBoard();

    assertThat(setScoreBoard.getLeadPlayer()).isEmpty();
    assertThat(gameScoreBoard.getPlayerScore(gameScoreBoard.getLeadPlayer().get())).isEqualTo(3L);
  }


  @Test
  public void shouldSetGameStateAsDeuce() {
    String PLAYER_ONE = "player 1";
    String PLAYER_TWO = "player 2";
    MatchProcessor matchProcessor = new MatchProcessor(PLAYER_ONE, PLAYER_TWO, new MatchStatusConsoleLogger());
    matchProcessor.initializeMatch();

    matchProcessor.pointWonBy(PLAYER_ONE);
    matchProcessor.pointWonBy(PLAYER_ONE);
    matchProcessor.pointWonBy(PLAYER_TWO);
    matchProcessor.pointWonBy(PLAYER_ONE);
    matchProcessor.pointWonBy(PLAYER_TWO);
    matchProcessor.pointWonBy(PLAYER_TWO);


    ScoreBoard<SetPoint> setScoreBoard = matchProcessor.getMatch().getTennisSet().getScoreBoard();
    ScoreBoard<GamePoint> gameScoreBoard = matchProcessor.getMatch().getTennisSet().getCurrentGame().getScoreBoard();

    assertThat(setScoreBoard.getLeadPlayer()).isEmpty();
    assertThat(matchProcessor.getMatch().getTennisSet().getCurrentGame().getState()).isEqualTo(DEUCE);
    assertThat(gameScoreBoard.getPlayerScore(gameScoreBoard.getLeadPlayer().get())).isEqualTo(3L);
  }


}