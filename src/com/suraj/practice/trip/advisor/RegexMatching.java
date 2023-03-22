package com.suraj.practice.trip.advisor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatching {
  public static void main(String[] argv) throws Exception {

    Pattern pattern = Pattern.compile("pattern");
    Matcher matcher = pattern.matcher("hello pattern where are you. i am looking for pattern");

    // Find all matches
    while (matcher.find()) {
      // Get the matching string
      String match = matcher.group();
      System.out.println(match);
    }
  }

}
