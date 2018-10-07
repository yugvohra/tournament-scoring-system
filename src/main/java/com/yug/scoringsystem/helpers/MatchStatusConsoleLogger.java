package com.yug.scoringsystem.helpers;

import com.yug.scoringsystem.domain.Match;
import com.yug.scoringsystem.domain.game.TangibleStatus;
import com.yug.scoringsystem.domain.set.TennisSet;

import static com.yug.scoringsystem.domain.set.states.SetState.WON;

public class MatchStatusConsoleLogger implements IMatchStatusLogger {

  /**
   * this can be delegate to factory +decorator
   **/
  @Override
  public void logMatchStatus(Match match) {
    TennisSet set = match.getTennisSet();
    TangibleStatus setStatus = TennisStatusHelper.findStatusOfSet(set);
    System.out.println(TennisStatusHelper.getStatusMessageOfSet(set));
    if (setStatus.getState() != WON)
      System.out.println(TennisStatusHelper.getStatusMessageOfGame(set.getCurrentGame()));
  }

}
