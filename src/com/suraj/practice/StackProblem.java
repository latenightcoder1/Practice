package com.suraj.practice;

import java.util.Stack;

public class StackProblem {

  public static void main(String[] args) {
    System.out.println(new StackProblem().solution("inaapauuaut",2));
  }

  private String solution(String s, int k) {
    Stack<int[]> stk = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      if (stk.size() > 0 && stk.peek()[0] == s.charAt(i)) {
        stk.peek()[1]++;
      } else {
        stk.push(new int[]{s.charAt(i), 1});
      }
      if (stk.peek()[1] == k) {
        stk.pop();
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!stk.isEmpty()) {
      int[] top = stk.pop();
      while (top[1] > 0) {
        sb.append((char) top[0]);
        top[1]--;
      }
    }
    return sb.reverse().toString();
  }


}
