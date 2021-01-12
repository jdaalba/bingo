package com.jdaalba.bingo;

import com.jdaalba.bingo.exception.UnexpectedException;
import com.jdaalba.bingo.messaging.EventChannel;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Publisher implements Callable<Void> {

  private final Queue<Integer> numbers;

  private final EventChannel eventChannel;

  private final AtomicBoolean running;

  public Publisher(final EventChannel eventChannel, final AtomicBoolean running) {
    System.out.println("Starting publisher in thread " + Thread.currentThread().getName());
    final var numbers = IntStream.rangeClosed(1, 90).boxed().collect(Collectors.toList());
    Collections.shuffle(numbers);
    this.numbers = new ArrayDeque<>(numbers);
    this.eventChannel = eventChannel;
    this.running = running;
  }

  @Override
  public final Void call() {
    while (running.get()) {
      if (numbers.isEmpty()) {
        throw new UnexpectedException("Something is wrong");
      }
      final var event = numbers.poll();
      System.out.println("Publishing: " + event);
      eventChannel.add(event);
    }
    return null;
  }
}

