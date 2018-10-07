package com.yug.scoringsystem.helpers;

import com.yug.scoringsystem.domain.Match;

public interface IMatchStatusLogger {
  void logMatchStatus(Match match);
}
