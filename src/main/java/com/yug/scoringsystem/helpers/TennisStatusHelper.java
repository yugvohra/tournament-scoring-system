package com.yug.scoringsystem.helpers;

import com.yug.scoringsystem.converters.ClockIGameStringConverter;
import com.yug.scoringsystem.converters.IGameStringConverter;
import com.yug.scoringsystem.converters.SetMessageConverter;
import com.yug.scoringsystem.converters.TieBreakerStringConverter;
import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.TangibleStatus;
import com.yug.scoringsystem.domain.set.TennisSet;

import static com.yug.scoringsystem.domain.game.GameType.NORMAL;

class TennisStatusHelper {

  static TangibleStatus findStatusOfSet(TennisSet set) {
    return new TangibleStatus(set.getState(), set.getScoreBoard());

  }

  private static TangibleStatus findStatusOfGame(Game game) {
    return new TangibleStatus(game.getState(), game.getScoreBoard());
  }

  static String getStatusMessageOfGame(Game game) {
    IGameStringConverter messageConverter = game.getGameType() == NORMAL ? ClockIGameStringConverter.getInstance() : TieBreakerStringConverter.getInstance();
    return messageConverter.fetchStringMessageFrom(findStatusOfGame(game));
  }

  static String getStatusMessageOfSet(TennisSet aSet) {
    return SetMessageConverter.getInstance().fetchStringMessageFrom(findStatusOfSet(aSet));
  }

}
