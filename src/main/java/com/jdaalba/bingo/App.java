package com.jdaalba.bingo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;

public class App {

  public static void main(String[] args) throws InterruptedException, IOException {

    System.out.println(Files.readString(Path.of("src/main/resources/banner.txt")));

    final var executorService = Executors.newFixedThreadPool(5);

    executorService.submit(Factory.getSubscriber("John"));
    executorService.submit(Factory.getSubscriber("Jane"));
    executorService.submit(Factory.getSubscriber("Jack"));
    executorService.submit(Factory.getSubscriber("Jill"));
    Thread.sleep(1_000L);
    executorService.submit(Factory.getPublisher());
  }
}