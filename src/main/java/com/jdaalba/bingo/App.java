package com.jdaalba.bingo;

import com.jdaalba.bingo.util.BannerUtil;
import java.util.concurrent.Executors;

public class App {

  public static void main(String[] args) {

    BannerUtil.printBanner();

    final var executorService = Executors.newFixedThreadPool(5);

    executorService.submit(Factory.getSubscriber("John"));
    executorService.submit(Factory.getSubscriber("Jane"));
    executorService.submit(Factory.getSubscriber("Jack"));
    executorService.submit(Factory.getSubscriber("Jill"));

    executorService.submit(Factory.getPublisher());
  }
}