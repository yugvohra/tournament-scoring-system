package com.yug.scoringsystem.helpers;

import com.yug.scoringsystem.converters.game.NormalGameStringConverter;
import com.yug.scoringsystem.converters.game.IGameStringConverter;
import com.yug.scoringsystem.converters.set.SetMessageConverter;
import com.yug.scoringsystem.converters.game.TieBreakerGameStringConverter;
import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.TangibleStatus;
import com.yug.scoringsystem.domain.set.TennisSet;

import static com.yug.scoringsystem.domain.game.GameType.NORMAL;

public class TennisStatusHelper {

  public static TangibleStatus findStatusOfSet(TennisSet set) {
    return new TangibleStatus(set.getSetState(), set.getScoreBoard());

  }

  private static TangibleStatus findStatusOfGame(Game game) {
    return new TangibleStatus(game.getState(), game.getScoreBoard());
  }

  public static String getStatusMessageOfGame(Game game) {
    IGameStringConverter messageConverter = game.getGameType() == NORMAL ? NormalGameStringConverter.getInstance() : TieBreakerGameStringConverter.getInstance();
    return messageConverter.fetchStringMessageFrom(findStatusOfGame(game));
  }

  public static String getStatusMessageOfSet(TennisSet aSet) {
    return SetMessageConverter.getInstance().fetchStringMessageFrom(findStatusOfSet(aSet));
  }

}
