package com.jdaalba.bingo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BannerUtil {

  public static void printBanner() {
    try {
      System.out.println(Files.readString(Path.of("src/main/resources/banner.txt")));
    } catch (IOException e) {
      throw new RuntimeException("Banner not found");
    }
  }
}
