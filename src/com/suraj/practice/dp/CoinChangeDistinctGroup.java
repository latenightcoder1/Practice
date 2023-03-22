package com.suraj.practice.dp;

import java.util.Arrays;

public class CoinChangeDistinctGroup {

  public static void main(String[] args) {
    System.out.println(new CoinChangeDistinctGroup().count(new int[]{2, 5, 3, 6}, 4, 10));
  }

  public long count(int S[], int m, int n) {
    long[] currentRow = new long[n + 1];
    long[] previousRow;
    Arrays.fill(currentRow, 0);
    currentRow[0] = 1;
    previousRow = currentRow;
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        long noOfWaysIfIncludedThisCoin = 0;
        if (j - S[i - 1] >= 0) {
          noOfWaysIfIncludedThisCoin = currentRow[j - S[i - 1]];
        }
        long noOfWaysIfThisCoinNotIncluded = previousRow[j];
        currentRow[j] = noOfWaysIfIncludedThisCoin + noOfWaysIfThisCoinNotIncluded;
      }
      previousRow = currentRow;
    }
    return currentRow[n];
  }

}
