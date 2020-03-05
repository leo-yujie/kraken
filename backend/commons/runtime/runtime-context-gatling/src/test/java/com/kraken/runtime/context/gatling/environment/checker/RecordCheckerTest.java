package com.kraken.runtime.context.gatling.environment.checker;

import com.google.common.collect.ImmutableMap;
import com.kraken.runtime.context.gatling.environment.checker.RecordChecker;
import com.kraken.runtime.entity.task.TaskType;
import com.kraken.test.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.kraken.tools.environment.KrakenEnvironmentKeys.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RecordChecker.class})
public class RecordCheckerTest {

  @Autowired
  RecordChecker checker;

  @Test
  public void shouldTest() {
    assertThat(checker.test(TaskType.RUN)).isFalse();
    assertThat(checker.test(TaskType.DEBUG)).isFalse();
    assertThat(checker.test(TaskType.RECORD)).isTrue();
  }


  @Test(expected = NullPointerException.class)
  public void shouldFailCheck() {
    checker.accept(ImmutableMap.of());
  }

  @Test
  public void shouldSucceed() {
    final var env = ImmutableMap.<String, String>builder()
        .put(KRAKEN_GATLING_SIMULATION_CLASS, "value")
        .put(KRAKEN_GATLING_SIMULATION_PACKAGE, "value")
        .put(KRAKEN_GATLING_HAR_PATH_REMOTE, "value")
        .put(KRAKEN_ANALYSIS_URL, "value")
        .put(KRAKEN_STORAGE_URL, "value")
        .build();
    checker.accept(env);
  }

  @Test
  public void shouldTestUtils() {
    TestUtils.shouldPassNPE(RecordChecker.class);
  }
}