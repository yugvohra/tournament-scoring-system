package com.yug.scoringsystem.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AuxillaaryClass for holding the scores and lead. This helps in quick lookup
 */
public class GameScore {
  private Map<Player, Long> playerScores;
  private Player leadPlayer;
  private Long lead;

  GameScore(List<Player> participatingPlayers) {
    initialize(participatingPlayers);
  }

  private void initialize(List<Player> participatingPlayers) {
    lead = 0L;
    playerScores = participatingPlayers.parallelStream().collect(Collectors.toMap(player -> player, player -> 0L));
  }

 public Optional<Player> getLeadPlayer() {
    return Optional.ofNullable(leadPlayer);
  }


  public Long getPlayerScore(Player aPlayer) {
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

}