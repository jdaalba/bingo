package com.jdaalba.bingo;

import com.jdaalba.bingo.messaging.EventChannel;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Subscriber implements Callable<Void> {

  private final String name;

  private final EventChannel eventChannel;

  private final AtomicBoolean running;

  private final List<Integer> board;

  private final List<Integer> boardCopy;

  private int counter = 0;

  public Subscriber(final String name, final EventChannel eventChannel, final AtomicBoolean running) {
    final var numbers = IntStream.rangeClosed(1, 90).boxed().collect(Collectors.toList());
    Collections.shuffle(numbers);
    this.board = numbers.subList(0, 15);
    board.sort(Comparator.naturalOrder());
    boardCopy = List.copyOf(board);
    this.name = name;
    this.eventChannel = eventChannel;
    this.running = running;
    System.out.println("Starting player " + name + " with numbers \n" +  printBoard());
  }

  @Override
  public final Void call() {
    while (running.get()) {
      if (counter < eventChannel.size()) {
        counter++;
        final var event = eventChannel.last();
        if (board.remove(event) && board.isEmpty() && running.get()) {
          running.set(false);
          System.out.println(name + ": @@@ BINGO @@@");
          System.out.println(printBoard());
          System.exit(1);
        }
      }
    }
    return null;
  }

  public final String  printBoard() {
    final StringBuilder sb = new StringBuilder("__________________________").append("\n");
    for (int i = 0; i < 3; i++) {
      final var subList = boardCopy.subList(i * 5, (i + 1) * 5).stream()
          .map(String::valueOf)
          .map(n -> String.format("%1$" + 2 + "s", n))
          .collect(Collectors.joining(" | "));
      sb.append("| ").append(subList).append(" |").append("\n");
    }
    return sb.append("__________________________").toString();
  }
}