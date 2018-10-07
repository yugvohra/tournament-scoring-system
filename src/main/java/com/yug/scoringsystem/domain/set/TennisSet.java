package com.yug.scoringsystem.domain.set;

import com.yug.scoringsystem.domain.IState;
import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.TennisIntegral;
import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.GameType;

import java.util.Set;

import static com.yug.scoringsystem.domain.set.SetState.UNDECIDED;

public class TennisSet {
  private final String setId;
  private TennisIntegral<SetPoint> tennisIntegral;
  private Game currentGame;
  private IState<SetPoint> setState;

  public TennisSet(String setId, Player playerOne, Player playerTwo) {
    this.setId = setId;
    /**First game should be of type normal*/
    currentGame = new Game(playerOne, playerTwo, GameType.NORMAL);
    tennisIntegral = new TennisIntegral<>(playerOne, playerTwo);
    setState = UNDECIDED;
  }

  public TennisSet(Player playerOne, Player playerTwo) {
    this(String.format("%s VS %s", playerOne.getName(), playerTwo.getName()), playerOne, playerTwo);
  }

  public IState<SetPoint> getSetState() {
    return setState;
  }

  public Game getCurrentGame() {
    return currentGame;
  }

  public void addANewGaMe() {
    if (this.setState == SetState.UNDECIDED)
      currentGame = new Game(getTennisIntegral().getScoreBoard().getParticipatingPlayers(), GameType.NORMAL);
    else if (this.setState == SetState.TIE)
      currentGame = new Game(getTennisIntegral().getScoreBoard().getParticipatingPlayers(), GameType.TIE_BREAKER);
  }


  public String getSetId() {
    return setId;
  }

  protected TennisIntegral<SetPoint> getTennisIntegral() {
    return tennisIntegral;
  }

  public Set<SetPoint> getPoints() {
    return getTennisIntegral().getPoints();
  }

  public void addAPoint(SetPoint aPoint) {
    if (!this.setState.canTransition())
      return;
    getTennisIntegral().addAPoint(aPoint);
    this.setState = setState.nextState(getTennisIntegral().getScoreBoard());
  }

  public ScoreBoard getScoreBoard() {
    return getTennisIntegral().getScoreBoard();
  }

}
