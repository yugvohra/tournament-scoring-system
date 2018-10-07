package com.yug.scoringsystem.converters.game;

import com.yug.scoringsystem.domain.TangibleStatus;

public interface IGameStringConverter {
  String fetchStringMessageFrom(TangibleStatus aTangibleStatus);
}
