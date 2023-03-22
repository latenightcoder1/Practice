package com.suraj.practice.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ArrayBurstProblem {

  public static void main(String[] args) {
    System.out.println(getShrunkArray(Arrays.asList("a", "b", "c", "c", "c", "d", "e", "e"), 3));
    System.out.println(getShrunkArray(Arrays.asList("a", "b", "c", "d", "e", "e", "e", "e","d","d","c","b","f","g","f"), 3));
  }

  private static List<String> getShrunkArray(List<String> inputArray, int burstLength) {
    List<String> output = new ArrayList<>();
    if(burstLength==1){
      return output;
    }
    Stack<String> stack = new Stack<>();
    String lastBurst="";
    for (int i = inputArray.size() - 1; i >= 0; i--) {
      if(inputArray.get(i).equals(lastBurst)){
        continue;
      }
      if (stack.isEmpty() || !stack.peek().equals(inputArray.get(i))) {
        stack.push(inputArray.get(i));
      } else {
        int count = burstLength - 1;
        Stack<String> tempStack = new Stack<>();
        while (count > 0) {
          if (!stack.isEmpty() && stack.peek().equals(inputArray.get(i))) {
            tempStack.push(stack.pop());
            count--;
          } else {
            break;
          }
        }
        if (count != 0) {
          while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
          }
          stack.push(inputArray.get(i));
          lastBurst="";
        }else{
          lastBurst=inputArray.get(i);
        }
      }
    }

    while (!stack.isEmpty()) {
      output.add(stack.pop());
    }
    return output;
  }

}
