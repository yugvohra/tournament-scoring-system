package com.yug.scoringsystem.helpers;

import com.yug.scoringsystem.converters.ClockGameStringConverter;
import com.yug.scoringsystem.converters.ITangibleStatusToMessageConverter;
import com.yug.scoringsystem.converters.TangibleStatusMessageConverter;
import com.yug.scoringsystem.domain.game.Game;
import com.yug.scoringsystem.domain.game.TangibleStatus;
import com.yug.scoringsystem.domain.set.TennisSet;
import com.yug.scoringsystem.rules.game.GameRuleEngineFactory;
import com.yug.scoringsystem.rules.set.SetRuleEngine;

import static com.yug.scoringsystem.domain.game.GameType.NORMAL;

public class TennisStatusHelper {

  public static TangibleStatus findStatusOfSet(TennisSet set) {
    return SetRuleEngine.getInstance().determineSetState(set);

  }

  public static TangibleStatus findStatusOfGame(Game game) {
    return GameRuleEngineFactory.getInstance().getRuleEngine(game.getGameType()).determineGameState(game);
  }

  public static String getStatusMessageOfGame(Game game)
  {
    ITangibleStatusToMessageConverter messageConverter=game.getGameType()== NORMAL? ClockGameStringConverter.getInstance(): TangibleStatusMessageConverter.getInstance();
    return messageConverter.fetchStringMessageFrom(findStatusOfGame(game));
  }
  public static String getStatusMessageOfSet(TennisSet aSet)
  {
    return TangibleStatusMessageConverter.getInstance().fetchStringMessageFrom(findStatusOfSet(aSet));
  }

}
