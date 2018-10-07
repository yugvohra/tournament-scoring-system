package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.game.TangibleStatus;
import com.yug.scoringsystem.helpers.ScoreMessageHelper;

import java.util.StringJoiner;

public class ClockGameStringConverter implements ITangibleStatusToMessageConverter {
  private static ClockGameStringConverter INSTANCE = new ClockGameStringConverter();

  private ClockGameStringConverter() {

  }

  public static ClockGameStringConverter getInstance() {
    return INSTANCE;
  }

  public String fetchStringMessageFrom(TangibleStatus aTangibleStatus) {
    ScoreBoard<GamePoint> scoreBoard = aTangibleStatus.getScoreBoard();
    StringJoiner stringJoiner = new StringJoiner(",");
    switch (aTangibleStatus.getState()) {
      case UNDECIDED:
        stringJoiner.add("Game is ongoing");
        scoreBoard.getParticipatingPlayers().forEach(player -> stringJoiner.add(player.getName() + " " + ScoreMessageHelper.getClockScoreForPoints(scoreBoard.getPlayerScore(player))));
        return stringJoiner.toString();
      case DEUCE:
        stringJoiner.add("Game is in Deuce");
        scoreBoard.getParticipatingPlayers().forEach(player -> stringJoiner.add(player.getName() + " " + ScoreMessageHelper.getClockScoreForPoints(scoreBoard.getPlayerScore(player))));
        return stringJoiner.toString();
      case ADVANTAGE:
        return "Advantage to " + scoreBoard.getLeadPlayer().get().getName();
      case WON:
        return "Game won by " + scoreBoard.getLeadPlayer().get().getName();

    }
    return null;
  }
}
