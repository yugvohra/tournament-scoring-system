package com.yug.scoringsystem.domain;


import java.util.*;

public class Game {
  private final String gameId;
  private final Player playerOne;
  private final Player playerTwo;
  private Set<GamePoint> points;
  private GameScore gameScore;

  public Game(String gameId, Player playerOne, Player playerTwo) {
    this.gameId = gameId;
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    initializeGame();
  }

  public Game(Player playerOne, Player playerTwo) {
    this(String.format("%s VS %s", playerOne.getName(), playerTwo.getName()), playerOne, playerTwo);
  }


  private void initializeGame() {
    points = new HashSet<>(16);
    gameScore = new GameScore();
  }

  public Player getPlayerOne() {
    return playerOne;
  }

  public Player getPlayerTwo() {
    return playerTwo;
  }

  public String getGameId() {
    return gameId;
  }

  public Set<GamePoint> getPoints() {
    return points;
  }

  public void addAPoint(GamePoint aPoint) {
    points.add(aPoint);
    this.getGameScore().addPoint(aPoint);
  }

  public Optional<Player> getLeadingPlayer()
  {
    return Optional.ofNullable(getGameScore().getLeadPlayer());
  }

  public Long getPlayerScore(Player aPlayer)
  {
    return getGameScore().getPlayerScores(aPlayer);
  }

  public GameScore getGameScore() {
    return gameScore;
  }

  /**
   * AuxillaaryClass for holding the scores and lead. This helps in quick lookup
   */
  private class GameScore {
    private Map<Player, Long> playerScores;
    private Player leadPlayer;

    public GameScore() {
      initialize();
    }

    public void initialize() {
      playerScores = new HashMap<>();
      playerScores.put(getPlayerOne(), 0l);
      playerScores.put(getPlayerTwo(), 0l);
    }

    public Player getLeadPlayer() {
      return leadPlayer;
    }


    public Long getPlayerScores(Player aPlayer) {
      return playerScores.get(aPlayer);
    }

    public void addPoint(GamePoint aPoint) {
      adJustLead(aPoint.getScoringPlayer());
    }

    private void adJustLead(Player scoringPlayer) {
      Long currentScore = playerScores.get(scoringPlayer);
      currentScore++;
      leadPlayer = leadPlayer == null || currentScore > playerScores.get(leadPlayer) ? scoringPlayer : leadPlayer;
      playerScores.put(scoringPlayer,currentScore);
    }
  }

}
