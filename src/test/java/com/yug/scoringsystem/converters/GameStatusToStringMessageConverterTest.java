package com.yug.scoringsystem.converters;

import com.yug.scoringsystem.domain.GameScore;
import com.yug.scoringsystem.domain.GameStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameStatusToStringMessageConverterTest {

  @Mock
  private GameScore aGameScore;
  @Mock
  private GameStatus aGameStatus;

  @Before
  public void setUp() {
    when(aGameStatus.getGameScore()).thenReturn(aGameScore);
  }

  @Test
  public void shouldReturnCorrectMessageForUndecidedGame() {
    //given
    when(aGameScore.getPlayerScore(any())).thenReturn(2L);
    //when
    String message = GameStatusToStringMessageConverter.getInstance().fetchStringMessageFrom(aGameStatus);

    assertThat(message).isEqualTo("score is 30-30");

  }

}