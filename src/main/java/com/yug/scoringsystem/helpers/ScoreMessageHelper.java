package com.yug.scoringsystem.helpers;

public class ScoreMessageHelper {
  public static Long getClockScoreForPoints(Long points)
  {
    Long clockScore=15*points;
    return clockScore<=40?clockScore:40;
  }
}
