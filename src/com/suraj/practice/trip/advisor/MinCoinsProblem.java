package com.suraj.practice.trip.advisor;

import java.util.Arrays;

/**
 * Given a list of coins and the total amount.
 * Calculate the minimum number of coins can be used to reach the total amount.
 */
public class MinCoinsProblem {

  public static void main(String[] args) {
    System.out.println(
        getMinCoins(new int[]{1,2,5}, 11));
  }

  //min no of coins to reach an amount
  private static int getMinCoins(int[] coins, int amount) {
    Arrays.sort(coins);
    int[] dp=new int[amount+1];
    Arrays.fill(dp,amount+2);
    dp[0]=0;
    for(int i=1;i<=amount;i++){
      for(int j=0;j<coins.length;j++){
        if(coins[j]<=i){
          dp[i]=Math.min(dp[i],1+dp[i-coins[j]]);
        }else{
          break;
        }
      }
    }
    return dp[amount]>amount?-1:dp[amount];
  }

}
