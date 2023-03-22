package com.suraj.practice.ds;

import java.util.EmptyStackException;
import java.util.Stack;

public class StackProblem {
  private static int stackM(String s){
    String[] array=s.split(" ");
    Stack<Integer> stack=new Stack<>();
    try {
      for (String token : array) {
        if (token.equals("DUP")) {
          stack.push(stack.peek());
        } else if (token.equals("+")) {
          stack.push(stack.pop() + stack.pop());
        } else if (token.equals("-")) {
          stack.push(stack.pop() - stack.pop());
        } else if (token.equals("POP")) {
          stack.pop();
        } else {
          stack.push(Integer.valueOf(token));
        }
      }
    }catch (EmptyStackException e){
      return -1;
    }
    return stack.pop();
  }

  public static void main(String[] args) {
    System.out.println(stackM("4 5 6 - 7 +"));
    System.out.println(stackM("13 DUP 4 POP 5 DUP + DUP + -"));
    System.out.println(stackM("5 6 + -"));
    System.out.println(stackM("3 DUP 5 - -"));
    System.out.println(stackM("1048575 DUP +"));
  }

}
