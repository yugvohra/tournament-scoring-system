package com.yug.scoringsystem.domain.game;


import com.yug.scoringsystem.domain.IState;
import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.TennisIntegral;

import java.util.Set;
import java.util.StringJoiner;

import static com.yug.scoringsystem.domain.game.GameState.UNDECIDED;
import static com.yug.scoringsystem.domain.game.GameState.UNDECIDED_TIEBREAKER;

public class Game {
  private final String gameId;
  private final GameType gameType;
  private TennisIntegral<GamePoint> tennisIntegral;
  private IState<GamePoint> gameState;

  public Game(String gameId, Player playerOne, Player playerTwo, GameType gameType) {
    this.gameId = gameId;
    this.gameType = gameType;
    tennisIntegral = new TennisIntegral<>(playerOne, playerTwo);
    gameState = gameType == GameType.NORMAL ? UNDECIDED : UNDECIDED_TIEBREAKER;
  }

  public Game(Set<Player> participatingPlayers, GameType gameType) {
    StringJoiner stringJoiner = new StringJoiner(" VS ");
    participatingPlayers.parallelStream().forEach(player -> stringJoiner.add(player.getName()));
    this.gameId = stringJoiner.toString();
    this.gameType = gameType;
    tennisIntegral = new TennisIntegral<>(participatingPlayers);
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
    if (!gameState.canTransition())
      return;
    getTennisIntegral().addAPoint(aPoint);
    this.gameState = gameState.nextState(getTennisIntegral().getScoreBoard());
  }

  public ScoreBoard getScoreBoard() {
    return getTennisIntegral().getScoreBoard();
  }

  public IState<GamePoint> getState() {
    return this.gameState;
  }
}
