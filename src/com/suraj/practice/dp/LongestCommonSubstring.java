package com.suraj.practice.dp;

public class LongestCommonSubstring {

  public static void main(String[] args) {
    System.out.println(longestCommonSubstr("ABCDGH","ACDGHR",6,6));
  }

  static int longestCommonSubstr(String S1, String S2, int n, int m){
    int[][] dp=new int[2][m+1];
    int maxLength=0,endIndex=0;
    for(int i=1;i<=n;i++){
      for(int j=1;j<=m;j++){
        dp[1][j]=0;
        if(S1.charAt(i-1)==S2.charAt(j-1)){
          dp[1][j]=1+dp[0][j-1];
          if(dp[1][j]>maxLength){
            maxLength=dp[1][j];
            endIndex=i;
          }
        }
      }
      for(int k=0;k<=m;k++){
        dp[0][k]=dp[1][k];
      }
    }
    return maxLength;
    // return S1.substring(endIndex-maxLength,endIndex);
  }

}
