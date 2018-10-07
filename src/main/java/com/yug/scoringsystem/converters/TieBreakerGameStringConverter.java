package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.ScoreBoard;
import com.yug.scoringsystem.domain.game.GamePoint;
import com.yug.scoringsystem.domain.game.states.GameState;
import com.yug.scoringsystem.domain.TangibleStatus;

import java.util.StringJoiner;

public class TieBreakerGameStringConverter implements IGameStringConverter {
  private static TieBreakerGameStringConverter INSTANCE = new TieBreakerGameStringConverter();

  private TieBreakerGameStringConverter() {

  }

  public static TieBreakerGameStringConverter getInstance() {
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
