package com.suraj.practice.ds;

import java.util.Stack;

public class MSSolution {

  public static void main(String[] args) {
    System.out.println(solution("CrCellBax"));
    System.out.println(solution("CgCoodlBClCuck"));
    System.out.println(solution("aCaBBCCab"));
    System.out.println(solution("aBB").equals(""));
  }

  private static String solution(String s) {
    Stack<Character> stack = new Stack<>();
    boolean capsLock = false;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == 'C') {
        capsLock = !capsLock;
      } else if (s.charAt(i) == 'B') {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else {
        stack.push(capsLock ? Character.toUpperCase(s.charAt(i)) : s.charAt(i));
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    return sb.reverse().toString();
  }
}
