package com.jdaalba.bingo;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;

import com.jdaalba.bingo.exception.UnexpectedException;
import com.jdaalba.bingo.messaging.EventChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
class PublisherTest {

  @Mock
  private EventChannel eventChannel;

  private AtomicBoolean running;

  private Publisher publisher;

  @BeforeEach
  void setUp() {
    running = new AtomicBoolean(true);
    publisher = new Publisher(eventChannel, running);
  }

  @Test
  void executeWhenHasNoSubscribers() {
    doNothing().when(eventChannel).add(anyInt());

    assertThatThrownBy(() -> publisher.call())
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Something is wrong");
  }

  @Test
  void execute() {
    final Answer<Void> customAnswer = invocation -> {
      running.set(false);
      return null;
    };
    doAnswer(customAnswer).when(eventChannel).add(anyInt());
    assertThatCode(() -> publisher.call()).doesNotThrowAnyException();
  }
}