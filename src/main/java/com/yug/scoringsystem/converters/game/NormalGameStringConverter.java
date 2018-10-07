package com.yug.scoringsystem.converters.game;

import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.game.GameState;
import com.yug.scoringsystem.domain.TangibleStatus;
import com.yug.scoringsystem.helpers.NormalGameMessageHelper;

import java.util.StringJoiner;

public class NormalGameStringConverter implements IGameStringConverter {
  private static NormalGameStringConverter INSTANCE = new NormalGameStringConverter();

  private NormalGameStringConverter() {
  }

  public static NormalGameStringConverter getInstance() {
    return INSTANCE;
  }

  public String fetchStringMessageFrom(TangibleStatus aTangibleStatus) {
    ScoreBoard<GamePoint> scoreBoard = aTangibleStatus.getScoreBoard();
    StringJoiner stringJoiner = new StringJoiner(",");
    switch ((GameState) aTangibleStatus.getState()) {
      case UNDECIDED:
        stringJoiner.add("Game is ongoing");
        scoreBoard.getParticipatingPlayers().forEach(player -> stringJoiner.add(player.getName() + " " + NormalGameMessageHelper.getClockScoreForPoints(scoreBoard.getPlayerScore(player))));
        return stringJoiner.toString();
      case DEUCE:
        stringJoiner.add("Game is in Deuce");
        scoreBoard.getParticipatingPlayers().forEach(player -> stringJoiner.add(player.getName() + " " + NormalGameMessageHelper.getClockScoreForPoints(scoreBoard.getPlayerScore(player))));
        return stringJoiner.toString();
      case ADVANTAGE:
        return "Advantage to " + scoreBoard.getLeadPlayer().get().getName();
      case WON:
        return "Game won by " + scoreBoard.getLeadPlayer().get().getName();

    }
    return null;
  }
}
