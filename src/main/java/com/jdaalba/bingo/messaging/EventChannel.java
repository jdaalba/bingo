package com.jdaalba.bingo.messaging;

public interface EventChannel {

  void add(Integer event);

  Integer last();

  int size();
}