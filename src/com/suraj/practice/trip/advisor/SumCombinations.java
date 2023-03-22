package com.suraj.practice.trip.advisor;

import java.util.Arrays;

/**
 * Given an array of non negative integers, and a non negative sum,
 *  write a method that returns the total number of pairs of integers
 *  that add up to the sum. The array can contain repeated numbers.
 */
public class SumCombinations {

  public static void main(String[] args) {
    System.out.println(
        getSumCombinationCount(new int[]{1, 1, 1, 14, 14, 14, 3, 3, 3, 12, 12, 3, 2, 13}, 15));
  }

  //getSumCombinationCount(new int[]{1, 1, 1, 14, 14, 14, 3, 3, 3, 12, 12, 3, 2, 13}, 15)
  private static int getSumCombinationCount(int[] array, int sum) {
    Arrays.sort(array);
    int count = 0;
    int start = 0, end = array.length - 1;
    while (start < end) {
      if (array[start] + array[end] == sum) {
        int equalLeft = 1;
        int equalRight = 1;
        while (array[start] == array[++start]) {
          equalLeft++;
        }
        while (array[end] == array[--end]) {
          equalRight++;
        }
        count += equalLeft * equalRight;
      } else if (array[start] + array[end] > sum) {
        end--;
      } else {
        start++;
      }
    }
    return count;
  }

}
