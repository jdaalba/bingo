package com.jdaalba.bingo;

import java.util.Stack;

public final class EventChannel {

  private final Stack<Integer> events = new Stack<>();

  public final void add(final Integer event) {
    events.add(event);
  }

  public final Integer last() {
    return events.peek();
  }

  public final int size() {
    return events.size();
  }
}