package com.suraj.practice.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CombinationSumWithRepeatingInput {

  public static void main(String[] args) {

    System.out.println(new CombinationSumWithRepeatingInput().combinationSum(
        new ArrayList<>(Arrays.asList(15, 8, 15, 10, 19, 18, 10, 3, 11, 7, 17)), 33));
  }

  ArrayList<ArrayList<Integer>> result;

  public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
    result=new ArrayList<>();
    Collections.sort(a);//3 7 8 10 10 11 15 15 17 18 19
    combinationSumUtil(a, b, 0, new ArrayList<>());
    return result;
  }

  private void combinationSumUtil(ArrayList<Integer> input, int sum, int index,
      ArrayList<Integer> curr) {
    if (sum < 0) {
      return;
    }
    if (sum == 0) {
      result.add(new ArrayList<>(curr));
      return;
    }
    if (index == input.size()) {
      return;
    }
    while (index>0 && input.get(index+1)==input.get(index)){
      index++;
    }

    combinationSumUtil(input, sum, index + 1, curr);

    curr.add(input.get(index));
    combinationSumUtil(input, sum - input.get(index), index + 1, curr);
    curr.remove(curr.size() - 1);
  }

}
