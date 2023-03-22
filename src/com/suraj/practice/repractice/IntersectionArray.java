package com.suraj.practice.repractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionArray {

  public int[] intersect(int[] nums1, int[] nums2) {
    List<Integer> output = new ArrayList<>();
    Map<Integer, Integer> integerCountMap1 = new HashMap<>();
    for (int a : nums1) {
      integerCountMap1.put(a, integerCountMap1.getOrDefault(a, 0) + 1);
    }
    for (int a : nums2) {
      Integer value = integerCountMap1.get(a);
      if (value != null && value != 0) {
        output.add(a);
        integerCountMap1.put(a, value - 1);
      }
    }
    int[] result = new int[output.size()];
    for (int i = 0; i < output.size(); i++) {
      result[i] = output.get(i);
    }
    return result;
  }
}