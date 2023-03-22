package com.suraj.practice.ds;

public class BinarySearchProblem {

  public static void main(String[] args) {
    System.out.println(getIndex(new int[]{1, 2, 5, 9, 9}, 1));
    System.out.println(getIndex(new int[]{1, 2, 5, 9, 9}, 5));
    System.out.println(getIndex(new int[]{1, 2, 5, 9, 9}, 2));
    System.out.println(getIndex(new int[]{1, 2, 5, 9, 9}, 9));
    System.out.println(getIndex(new int[]{1, 2, 5, 9, 9, 11}, 11));
    System.out.println(getIndex(new int[]{1, 2, 5,7,8, 9, 9, 11}, 7));
  }

  private static int getIndex(int[] ints, int i) {
    int N = ints.length;
    if (N == 0) {
      return -1;
    }
    int l = 0;
    int r = N - 1;
    while (l < r) {
      int m = (l + r) / 2;
      if (ints[m] > i) {
        r = m - 1;
      } else {
        l = m+1;
      }
      if (ints[m] == i) {
        return m;
      }
    }
    if (ints[l] == i) {
      return l;
    }

    return -1;
  }


}
