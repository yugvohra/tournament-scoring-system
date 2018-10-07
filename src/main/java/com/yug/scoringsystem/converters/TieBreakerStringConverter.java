package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.game.GameState;
import com.yug.scoringsystem.domain.game.TangibleStatus;

import java.util.StringJoiner;

public class TieBreakerStringConverter implements IGameStringConverter {
  private static TieBreakerStringConverter INSTANCE = new TieBreakerStringConverter();

  private TieBreakerStringConverter() {

  }

  public static TieBreakerStringConverter getInstance() {
    return INSTANCE;
  }

  public String fetchStringMessageFrom(TangibleStatus aTangibleStatus) {
    ScoreBoard<GamePoint> scoreBoard = aTangibleStatus.getScoreBoard();
    StringJoiner stringJoiner = new StringJoiner(",");
    switch ((GameState) aTangibleStatus.getState()) {
      case UNDECIDED_TIEBREAKER:
        stringJoiner.add("game state is");
        scoreBoard.getParticipatingPlayers().forEach(player -> stringJoiner.add(player.getName() + " " + scoreBoard.getPlayerScore(player)));
        return stringJoiner.toString();
      case WON:
        return "game won by " + scoreBoard.getLeadPlayer().get().getName();
    }
    return null;
  }
}
