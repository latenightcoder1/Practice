package com.suraj.practice.ds;

import java.util.HashSet;
import java.util.Set;

public class Solution {

  public static int solution1(String s){
    int sum=0;
    for(int i=0;i<s.length();i++){
      sum +=s.charAt(i)-48;
    }
    Set<Integer> set=new HashSet<>();
    for(int i=0;i<s.length();i++){
      int value=s.charAt(i)-48;
      int remainingSum=sum-value;
      for(int j=0;j<=9;j++){

      }
      if((remainingSum+i)%3==0 && i!=value){
      }

    }
    return set.size();
  }

  public static int solution(String s)
  {
    int sum = 0;
    for (int i = 0; i < s.length(); ++i) {
      sum += s.charAt(i) - 48;
    }
    int count = 0;
    for (int i = 0; i < s.length(); ++i) {
      int remaining_sum = sum - (s.charAt(i) - 48);
      for (int j = 0; j <= 9; ++j) {
        if ((remaining_sum + j) % 3 == 0 && j != s.charAt(i) - 48) {
          ++count;
        }
      }
    }
    if(Integer.valueOf(s)%3==0){
      ++count;
    }
    return count;
  }

  // Driver Code
  public static void main(String[] args)
  {

    System.out.println(solution("0081"));
    System.out.println(solution("23"));
    System.out.println(solution("022"));
  }

}
