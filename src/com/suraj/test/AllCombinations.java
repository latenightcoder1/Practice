package com.suraj.test;

import java.util.ArrayList;
import java.util.List;

public class AllCombinations {

  public static void main(String[] args) {

    List<String> result = new ArrayList<>();

    int[] input = new int[]{1, 2, 3, 4, 5, 6};
    int k = 3;
    populateAllSubset(input, result, 0, k, "");
    System.out.println(result);
  }

  private static void populateAllSubset(int[] input, List<String> result, int startIndex,
      int length, String current) {
Integer a=length;
    if (current.length() > length) {
      return;
    }
    result.add(current);
    for (int i = startIndex; i < input.length; i++) {
      populateAllSubset(input, result, i + 1, length, current + input[i]);
    }
  }

}
