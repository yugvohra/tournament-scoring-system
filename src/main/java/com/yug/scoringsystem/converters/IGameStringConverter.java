package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.game.TangibleStatus;

public interface IGameStringConverter {
  String fetchStringMessageFrom(TangibleStatus aTangibleStatus);
}
