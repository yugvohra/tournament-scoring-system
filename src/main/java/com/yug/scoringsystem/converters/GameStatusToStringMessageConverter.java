package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.GameScore;
import com.yug.scoringsystem.domain.GameStatus;
import com.yug.scoringsystem.helpers.ScoreMessageHelper;

import java.util.StringJoiner;

public class GameStatusToStringMessageConverter {
  private static GameStatusToStringMessageConverter INSTANCE = new GameStatusToStringMessageConverter();

  private GameStatusToStringMessageConverter() {

  }

  public static GameStatusToStringMessageConverter getInstance() {
    return INSTANCE;
  }

  public String fetchStringMessageFrom(GameStatus aGameStatus) {
    GameScore gameScore = aGameStatus.getGameScore();
    StringJoiner stringJoiner = new StringJoiner(",");
    switch (aGameStatus.getGameState()) {
      case UNDECIDED:
        stringJoiner.add("Game is ongoing");
        gameScore.getParticipatingPlayers().forEach(player -> stringJoiner.add(player.getName() + " " + ScoreMessageHelper.getClockScoreForPoints(gameScore.getPlayerScore(player))));
        return stringJoiner.toString();
      case DEUCE:
        stringJoiner.add("Deuce");
        gameScore.getParticipatingPlayers().forEach(player -> stringJoiner.add(player.getName() + " " + ScoreMessageHelper.getClockScoreForPoints(gameScore.getPlayerScore(player))));
        return stringJoiner.toString();
      case ADVANTAGE:
        return "Advantage to " + gameScore.getLeadPlayer().get().getName();
      case WON:
        return "Game won by " + gameScore.getLeadPlayer().get().getName();

    }
    return null;
  }
}
