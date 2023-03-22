package com.suraj.practice.repractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumTriplet {

  public static List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> list = new ArrayList<>();
    for (int i = 0; i < nums.length - 2; i++) {
      int lower = i + 1;
      int upper = nums.length - 1;
      while (lower < upper) {
        if (nums[i] + nums[lower] + nums[upper] == 0) {

          list.add(Arrays.asList(nums[i], nums[lower], nums[upper]));

          while (lower < upper && nums[lower] == nums[lower + 1]) {
            lower++;
          }
          while (lower < upper && nums[upper] == nums[upper - 1]) {
            upper--;
          }

          upper--;
          lower++;
        } else if (nums[i] + nums[lower] + nums[upper] > 0) {
          upper--;
        } else {
          lower++;
        }
      }
      while ((i < nums.length - 2) && nums[i] == nums[i + 1]) {
        i++;
      }
    }
    return list;
  }

  public static void main(String[] args) {
    System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));

  }

}
