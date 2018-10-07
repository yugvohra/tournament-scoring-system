package com.yug.scoringsystem.main;

import com.yug.scoringsystem.helpers.loggers.MatchStatusConsoleLogger;
import com.yug.scoringsystem.processors.MatchProcessor;

import static com.yug.scoringsystem.domain.set.SetState.WON;


public class TennisGameRunner {
  public static void main(String args[]) {
    String[] playersNames = {"Alfred", "Neo"};
    MatchProcessor matchProcessor = new MatchProcessor(playersNames[0], playersNames[1], new MatchStatusConsoleLogger());
    matchProcessor.initializeMatch();
    do {
      int playerIdx = (int) Math.round(Math.random());
      System.out.println("scoring playerName is " + playersNames[playerIdx]);
      matchProcessor.pointWonBy(playersNames[playerIdx]);
      matchProcessor.showScore();

    } while (matchProcessor.getMatch().getTennisSet().getSetState() != WON);
  }
}
