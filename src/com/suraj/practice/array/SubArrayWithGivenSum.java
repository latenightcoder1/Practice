package com.suraj.practice.array;

import java.util.ArrayList;

public class SubArrayWithGivenSum {

  public static void main(String[] args) {
    System.out.println(subarraySum(new int[]{1, 8}, 2, 8));
  }

  static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
    int start = 0;
    int sum = 0;
    int end = 0;
    for (int i = 0; i < arr.length; i++) {
      sum = sum + arr[i];
      while (sum > s) {
        sum = sum - arr[start++];
      }
      if (sum == s) {
        end = i;
        break;
      }
    }
    ArrayList<Integer> result = new ArrayList<>();
    if (end == 0) {
      result.add(-1);
    } else {
      result.add(start + 1);
      result.add(end + 1);
    }
    return result;
  }

}
