package com.jdaalba.bingo.messaging;

import java.util.Stack;

public final class EventChannelImpl implements EventChannel {

  private final Stack<Integer> events = new Stack<>();

  @Override
  public final void add(final Integer event) {
    events.add(event);
  }

  @Override public final Integer last() {
    return events.peek();
  }

  @Override public final int size() {
    return events.size();
  }
}