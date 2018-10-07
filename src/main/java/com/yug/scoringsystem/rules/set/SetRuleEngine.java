package com.yug.scoringsystem.rules.set;

import com.yug.scoringsystem.domain.Player;
import com.yug.scoringsystem.domain.game.TangibleStatus;
import com.yug.scoringsystem.domain.set.TennisSet;

import java.util.Optional;

public class SetRuleEngine {

  private static SetRuleEngine INSTANCE = new SetRuleEngine();

  private SetRuleEngine() {

  }

  public static SetRuleEngine getInstance() {
    return INSTANCE;
  }

  /**TODO
   * move this to state machine
   * */
  public TangibleStatus   determineSetState(TennisSet aSet) {
    Optional<Player> leadingPlayer = aSet.getScoreBoard().getLeadPlayer();
    Long lead = aSet.getScoreBoard().getLead();
    /**player only wins when lead is greater than 2 and his/her score is 6 or when he scors 7 setpoints*/
    if ((lead >= 2 && aSet.getScoreBoard().getPlayerScore(leadingPlayer.get()) == 6)||(lead>=1&&aSet.getScoreBoard().getPlayerScore(leadingPlayer.get()) == 7))
      return new TangibleStatus(State.WON, aSet.getScoreBoard());
    //if set ties at 6-6
    else if (leadingPlayer.isPresent() && lead == 0 && aSet.getScoreBoard().getPlayerScore(leadingPlayer.get()) == 6)
      return new TangibleStatus(State.TIE, aSet.getScoreBoard());
    else
      //set is still ongoing in all other cases */
      return new TangibleStatus(State.UNDECIDED, aSet.getScoreBoard());
  }
}
