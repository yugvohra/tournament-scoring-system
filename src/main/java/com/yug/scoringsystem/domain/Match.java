package com.yug.scoringsystem.domain;

import com.yug.scoringsystem.domain.set.TennisSet;

public class Match {
  public TennisSet getTennisSet() {
    return tennisSet;
  }

  private final TennisSet tennisSet;

  public Match(TennisSet tennisSet) {
    this.tennisSet = tennisSet;
  }


}
