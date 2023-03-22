package com.work.practice;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringJoin {

  private static final String COMMA = ",";
  private static final char DOT = '.';

  public static void main(String[] args) {
    String input="startPingTest.request.serialNumber: must not be blank, startPingTest.request.count:"
        + " must be greater than or equal to 1, startPingTest.request.destination:"
        + " must not be blank";

    for(final String s:input.split(COMMA)){
      System.out.println(s.substring(s.indexOf(DOT)));
    }
    String msg= Arrays.stream(input.split(COMMA))
        .map(messageToken->messageToken.substring(messageToken.indexOf(DOT)+1))
        .collect(Collectors.joining(COMMA));
    System.out.println(msg);
  }

}
