package com.yug.scoringsystem.helpers;

public class ScoreMessageHelper {
  public static Long getClockScoreForPoints(Long points) {
    long clockScore = 15 * points;
    return clockScore <= 40 ? clockScore : 40;
  }
}
