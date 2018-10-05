package com.yug.scoringsystem.rules;


import com.yug.scoringsystem.domain.Game;
import com.yug.scoringsystem.domain.GameState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameRuleEngineTest {

  @Mock
  Game aGame;

  @Test
  public void shouldReturnUndecidedStateForOngoingGame()
  {
    //given

    GameRuleEngine gameRuleEngine=GameRuleEngine.getInstance();
    //when
    GameState fetchedState=gameRuleEngine.determineGameState(aGame);
    assertThat(fetchedState).isEqualTo(GameState.UNDECIDED);

  }


}