package com.yug.scoringsystem.domain.set;

import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.TennisIntegral;
import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.GameType;

import java.util.Set;

public class TennisSet {
  private final String setId;
  private TennisIntegral<SetPoint> tennisIntegral;
  private Game currentGame;

  public TennisSet(String setId, Player playerOne, Player playerTwo) {
    this.setId = setId;
    /**First game should be of type normal*/
    currentGame = new Game(playerOne, playerTwo, GameType.NORMAL);
    tennisIntegral = new TennisIntegral<>(playerOne, playerTwo);
  }

  public TennisSet(Player playerOne, Player playerTwo) {
    this(String.format("%s VS %s", playerOne.getName(), playerTwo.getName()), playerOne, playerTwo);
  }

  public Game getCurrentGame() {
    return currentGame;
  }

  public void setCurrentGame(Game currentGame) {
    this.currentGame = currentGame;
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
    getTennisIntegral().addAPoint(aPoint);
  }

  public ScoreBoard getScoreBoard() {
    return getTennisIntegral().getScoreBoard();
  }

}
