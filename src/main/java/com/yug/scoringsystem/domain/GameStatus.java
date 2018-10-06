package com.yug.scoringsystem.domain;

public class GameStatus {
  private final GameState gameState;
  private final GameScore gameScore;

  public GameStatus(GameState gameState, GameScore gameScore) {
    this.gameState = gameState;
    this.gameScore = gameScore;
  }

  public GameState getGameState() {
    return gameState;
  }

  public GameScore getGameScore() {
    return gameScore;
  }
}