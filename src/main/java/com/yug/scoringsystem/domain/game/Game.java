package com.yug.scoringsystem.domain.game;


import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.TennisIntegral;

import java.util.Set;

public class Game {
  private final String gameId;
  private final GameType gameType;
  private TennisIntegral<GamePoint> tennisIntegral;

  public Game(String gameId, Player playerOne, Player playerTwo, GameType gameType) {
    this.gameId = gameId;
    this.gameType = gameType;
    tennisIntegral = new TennisIntegral<>(playerOne, playerTwo);
  }

  public Game(Player playerOne, Player playerTwo, GameType gameType) {
    this(String.format("%s VS %s", playerOne.getName(), playerTwo.getName()), playerOne, playerTwo, gameType);
  }

  public GameType getGameType() {
    return gameType;
  }

  public TennisIntegral<GamePoint> getTennisIntegral() {
    return tennisIntegral;
  }

  public String getGameId() {
    return gameId;
  }

  public Set<GamePoint> getPoints() {
    return getTennisIntegral().getPoints();
  }

  public void addAPoint(GamePoint aPoint) {
    getTennisIntegral().addAPoint(aPoint);
  }

  public ScoreBoard getScoreBoard() {
    return getTennisIntegral().getScoreBoard();
  }

}
