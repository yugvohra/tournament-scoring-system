package com.yug.scoringsystem.helpers;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class NormalGameMessageHelperTest {

  @Test
  public void shouldReturnZero_For_0_points() {//given
    //when
    Long clockScore = NormalGameMessageHelper.getClockScoreForPoints(0L);
    assertThat(clockScore).isEqualTo(0L);
  }

  @Test
  public void shouldReturn15_For_1_points() {//given
    //when
    Long clockScore = NormalGameMessageHelper.getClockScoreForPoints(1L);
    assertThat(clockScore).isEqualTo(15L);
  }

  @Test
  public void shouldReturn_forty_For_4_points() {//given
    //when
    Long clockScore = NormalGameMessageHelper.getClockScoreForPoints(4L);
    assertThat(clockScore).isEqualTo(40L);
  }
}