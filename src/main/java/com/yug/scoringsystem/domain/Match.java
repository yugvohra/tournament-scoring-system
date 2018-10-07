package com.yug.scoringsystem.domain;

import com.yug.scoringsystem.domain.set.TennisSet;

public class Match {
  private final TennisSet tennisSet;

  public Match(TennisSet tennisSet) {
    this.tennisSet = tennisSet;
  }

  public TennisSet getTennisSet() {
    return tennisSet;
  }


}
