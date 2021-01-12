package com.jdaalba.bingo;

import com.jdaalba.bingo.messaging.EventChannel;
import com.jdaalba.bingo.messaging.EventChannelImpl;
import java.util.concurrent.atomic.AtomicBoolean;

public class Factory {

  private static final AtomicBoolean running;

  private static final EventChannel eventChannel;

  static {
    running = new AtomicBoolean(true);
    eventChannel = new EventChannelImpl();
  }

  public static Subscriber getSubscriber(final String name) {
    return new Subscriber(name, eventChannel, running);
  }

  public static Publisher getPublisher() {
    return new Publisher(eventChannel, running);
  }
}
