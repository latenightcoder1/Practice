package com.suraj.practice.ds;

import java.util.Arrays;

public class GSProblem1 {

  public static void main(String[] args) {
    System.out.println(noOfWaysToDrawTheGame(8));

  }

  private static int noOfWaysToDrawTheGame(int totalScore) {
    int[] dp = new int[totalScore + 1];
    Arrays.fill(dp, 0);
    dp[2] = 1;
    dp[4] = 2;
    for (int i = 2; i <= totalScore; i++) {
      dp[i] = dp[i] + dp[i - 2];
    }
    for (int i = 4; i <= totalScore; i++) {
      dp[i] = dp[i] + dp[i - 4];
    }
    for (int i = 6; i <= totalScore; i++) {
      dp[i] = dp[i] + dp[i - 6];
    }
    return dp[totalScore];
  }

}
