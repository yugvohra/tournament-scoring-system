package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.game.TangibleStatus;
import com.yug.scoringsystem.domain.set.states.SetState;

import java.util.StringJoiner;

public class SetMessageConverter {

  private static SetMessageConverter INSTANCE = new SetMessageConverter();

  private SetMessageConverter() {

  }

  public static SetMessageConverter getInstance() {
    return INSTANCE;
  }

  public String fetchStringMessageFrom(TangibleStatus aTangibleStatus) {
    ScoreBoard<GamePoint> scoreBoard = aTangibleStatus.getScoreBoard();
    StringJoiner stringJoiner = new StringJoiner(",");
    switch ((SetState) aTangibleStatus.getState()) {
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
