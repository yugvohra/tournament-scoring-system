package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.game.TangibleStatus;

import java.util.StringJoiner;

public class TangibleStatusMessageConverter implements ITangibleStatusToMessageConverter {

  private static TangibleStatusMessageConverter INSTANCE = new TangibleStatusMessageConverter();

  private TangibleStatusMessageConverter() {

  }

  public static TangibleStatusMessageConverter getInstance() {
    return INSTANCE;
  }

  public String fetchStringMessageFrom(TangibleStatus aTangibleStatus) {
    ScoreBoard<GamePoint> scoreBoard = aTangibleStatus.getScoreBoard();
    StringJoiner stringJoiner = new StringJoiner(",");
    switch (aTangibleStatus.getState()) {
      case UNDECIDED:
        stringJoiner.add("Set state is");
        scoreBoard.getParticipatingPlayers().forEach(player -> stringJoiner.add(player.getName() + " " + scoreBoard.getPlayerScore(player)));
        return stringJoiner.toString();
      case WON:
        return "Set won by " + scoreBoard.getLeadPlayer().get().getName();
      case TIE:
        return "Set is Tie";

    }
    return null;
  }
}
