package com.suraj.practice.ds;

import java.util.Arrays;
import java.util.List;

public class LiftingWeight {

  public static void main(String[] args) {
    System.out.println(weigthCapacity(Arrays.asList(1, 3, 5), 7));
    System.out.println(weigthCapacity(Arrays.asList(7,1,5,6,2), 7));
    System.out.println(weigthCapacity(Arrays.asList(4,8,5,9), 20));
  }

  //max weight that can be picked within max capacity
  //weigthCapacity(Arrays.asList(4,8,5,9), 20)
  private static int weigthCapacity(List<Integer> weights, int maxCapacity) {
    return weigthCapacity(weights, maxCapacity, weights.size());
  }

  private static int weigthCapacity(List<Integer> weights, int maxCapacity, int size) {
    if (size == 0 || maxCapacity == 0) {
      return 0;
    }
    if (weights.get(size - 1) > maxCapacity) {
      return weigthCapacity(weights, maxCapacity, size - 1);
    } else {
      return Math.max(
          weights.get(size - 1) + weigthCapacity(weights, maxCapacity - weights.get(size - 1), size - 1),
          weigthCapacity(weights, maxCapacity, size - 1));
    }
  }

}
