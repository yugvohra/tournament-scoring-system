package com.yug.scoringsystem.domain;


import java.util.*;

public class Game {
  private final String gameId;
  private final Player playerOne;
  private final Player playerTwo;
  private Set<GamePoint> points;
  private GameScore gameScore;

  Game(String gameId, Player playerOne, Player playerTwo) {
    this.gameId = gameId;
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    initializeGame();
  }

  Game(Player playerOne, Player playerTwo) {
    this(String.format("%s VS %s", playerOne.getName(), playerTwo.getName()), playerOne, playerTwo);
  }


  private void initializeGame() {
    points = new HashSet<>(16);
    gameScore = new GameScore();
  }

  private Player getPlayerOne() {
    return playerOne;
  }

  private Player getPlayerTwo() {
    return playerTwo;
  }

  String getGameId() {
    return gameId;
  }

  Set<GamePoint> getPoints() {
    return points;
  }

  void addAPoint(GamePoint aPoint) {
    points.add(aPoint);
    this.getGameScore().addPoint(aPoint);
  }

  public Optional<Player> getLeadingPlayer() {
    return Optional.ofNullable(getGameScore().getLeadPlayer());
  }

  public Long getPlayerScore(Player aPlayer) {
    return getGameScore().getPlayerScores(aPlayer);
  }

  private GameScore getGameScore() {
    return gameScore;
  }

  public Long getLead() {
    return getGameScore().getLead();
  }

  /**
   * AuxillaaryClass for holding the scores and lead. This helps in quick lookup
   */
  private class GameScore {
    Map<Player, Long> playerScores;
    Player leadPlayer;
    Long lead;

    GameScore() {
      initialize();
    }

    void initialize() {
      lead = 0L;
      playerScores = new HashMap<>();
      playerScores.put(getPlayerOne(), 0L);
      playerScores.put(getPlayerTwo(), 0L);
    }

    Player getLeadPlayer() {
      return leadPlayer;
    }


    Long getPlayerScores(Player aPlayer) {
      return playerScores.get(aPlayer);
    }

    public Long getLead() {
      return lead;
    }

    void addPoint(GamePoint aPoint) {
      adjustScoreBoard(aPoint.getScoringPlayer());
    }

    /**
     * TODO
     * Make it thread safe
     *
     * @param scoringPlayer
     */
    private void adjustScoreBoard(Player scoringPlayer) {
      Long playerScore = playerScores.get(scoringPlayer);
      playerScore++;
      if (leadPlayer == null || playerScore > playerScores.get(leadPlayer)) {
        leadPlayer = scoringPlayer;
        lead++;
      } else {
        lead--;
      }
      playerScores.put(scoringPlayer, playerScore);
    }

    private void adjustLead(Player scoringPlayer) {
      if (leadPlayer.equals(scoringPlayer))
        lead++;
      else
        lead--;
    }
  }

}
