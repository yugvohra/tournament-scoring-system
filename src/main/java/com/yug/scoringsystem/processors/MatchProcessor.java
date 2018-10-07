package com.yug.scoringsystem.processors;

import com.yug.scoringsystem.domain.Match;
import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.game.GameState;
import com.yug.scoringsystem.domain.set.SetPoint;
import com.yug.scoringsystem.domain.set.TennisSet;
import com.yug.scoringsystem.helpers.IMatchStatusLogger;
import com.yug.scoringsystem.helpers.MatchStatusConsoleLogger;

import java.util.HashMap;
import java.util.Map;

public class MatchProcessor {

  private Map<String, Player> nameMapping;
  private Player playerOne;
  private Player playerTwo;
  private Match match;
  /**
   * contender of IOC or proxy
   */
  private IMatchStatusLogger logger;

  public MatchProcessor(String playerOneName, String playerTwoName, MatchStatusConsoleLogger providedLogger) {
    playerOne = new Player(playerOneName);
    playerTwo = new Player(playerTwoName);
    logger = providedLogger;
    nameMapping = new HashMap<>();
    nameMapping.put(playerOneName, playerOne);
    nameMapping.put(playerTwoName, playerTwo);
  }

  public Match getMatch() {
    return match;
  }

  public void initializeMatch() {
    TennisSet tennisSet = new TennisSet(playerOne, playerTwo);
    match = new Match(tennisSet);
  }

  public void pointWonBy(String playerName) {
    Player scoringPlayer = nameMapping.get(playerName);
    //get current game
    Game onGoingGame = match.getTennisSet().getCurrentGame();
    //fetch status of game after the scored point
    onGoingGame.addAPoint(new GamePoint(scoringPlayer));

    if (onGoingGame.getState() == GameState.WON) {
      match.getTennisSet().addAPoint(new SetPoint(onGoingGame, scoringPlayer));
      match.getTennisSet().addANewGaMe();
    }
    //else keep on playing the same game
  }

  /**
   * contender of proxy or IOC
   */
  public void showScore() {
    logger.logMatchStatus(match);
  }
/*
  private TangibleStatus getStatusAfterScoredPoint(Game onGoingGame, GamePoint gamePoint) {
    onGoingGame.addAPoint(gamePoint);
    return findOutComeOfScoredPoint(onGoingGame);
  }*/
/*

  private void setNextState(TennisSet tennisSet) {
    */

  /**
   * will find the current status of the set WON , in TIE or UNDECIDED
   *//*

    TangibleStatus setStatus = TennisStatusHelper.findStatusOfSet(tennisSet);
    switch (setStatus.getState()) {
      case WON:
        break;
      case UNDECIDED:
        tennisSet.setCurrentGame(new Game(playerOne, playerTwo, GameType.NORMAL));
        break;
      case TIE:
        tennisSet.setCurrentGame(new Game(playerOne, playerTwo, GameType.TIE_BREAKER));
        break;
    }
  }
*/
 /* private TangibleStatus findOutComeOfScoredPoint(Game onGoingGame) {
    return TennisStatusHelper.findStatusOfGame(onGoingGame);
  }*/
}
