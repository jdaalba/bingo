package com.jdaalba.bingo;

import java.util.concurrent.atomic.AtomicBoolean;

public class Factory {

  private static final AtomicBoolean running;

  private static final EventChannel eventChannel;

  static {
    running = new AtomicBoolean(true);
    eventChannel = new EventChannel();
  }

  public static Subscriber getSubscriber(final String name) {
    return new Subscriber(name, eventChannel, running);
  }

  public static Publisher getPublisher() {
    return new Publisher(eventChannel, running);
  }
}
