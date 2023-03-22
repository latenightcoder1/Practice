package com.suraj.practice.ds;

import java.util.Arrays;
import java.util.List;

public class StatisticsIndicator {

  public static void main(String[] args) {
    System.out.println(
        difference_calculator(Arrays.asList(3, 3, 2, 2, 5, 5, 5, 5, 5, 3, 3, 3, 2, 2)));
    System.out.println(
        difference_calculator(Arrays.asList(1, 2, 2, 4, 4, 4, 4, 2, 2, 2)));
    System.out.println(
        difference_calculator(Arrays.asList(1, 2, 2, 2, 2, 3, 3, 3, 1, 1, 2, 2)));
    System.out.println(
        difference_calculator(Arrays.asList(1, 1, 3, 3, 3, 4, 4, 4, 3, 3, 3)));
    System.out.println(
        difference_calculator(Arrays.asList(4,4,4,4,5,5,5,5,5)));
    System.out.println(
        difference_calculator(Arrays.asList(2,3,3,3,5,5,5,5,5)));
    System.out.println(
        difference_calculator(Arrays.asList(2,2,3,3,3)));
  }

  private static int difference_calculator(List<Integer> arr) {
    int indicator1 = getIndicator1(arr);
    int indicator2 = getIndicator2(arr);
    if (indicator1 > indicator2) {
      return indicator1 - indicator2;
    } else {
      return indicator2 - indicator1;
    }
  }

  private static int getIndicator2(List<Integer> arr) {
    int indicator2 = 0;
    for (int i = 0; i < arr.size(); i++) {
      int value = arr.get(i);
      if (value == (i + 1) && (i + value) <= arr.size() && checkIfValueIsRepeated(value, i, arr,
          value + i)) {
        indicator2++;
      }
    }
    return indicator2;
  }

  private static boolean checkIfValueIsRepeated(int value, int startingIndex, List<Integer> arr,
      int endIndex) {
    for (int i = startingIndex; i < endIndex; i++) {
      if (arr.get(i) != value) {
        return false;
      }
    }
    if (endIndex + 1 < arr.size() && arr.get(endIndex ) == value) {
      return false;
    }
    return true;
  }

  private static int getIndicator1(List<Integer> arr) {
    int indicator1 = 0;
    int i = 1;
    int previous = arr.get(0);
    int count = 1;
    while (i < arr.size()) {
      if (arr.get(i) == previous) {
        count++;
      } else {
        if (previous == count) {
          indicator1++;
        }
        previous = arr.get(i);
        count = 1;
      }
      i++;
    }
    if (previous == count) {
      indicator1++;
    }
    return indicator1;
  }
}
