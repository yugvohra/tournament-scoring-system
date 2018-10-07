package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.game.TangibleStatus;

public interface ITangibleStatusToMessageConverter {
  String fetchStringMessageFrom(TangibleStatus aTangibleStatus);
}
