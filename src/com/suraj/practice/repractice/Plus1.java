package com.suraj.practice.repractice;

import java.util.Arrays;

public class Plus1 {

  public int[] plusOne(int[] digits) {
    for (int i = digits.length - 1; i >= 0; i--) {
      if (digits[i] < 9) {
        digits[i] = digits[i] + 1;
        return digits;
      } else {
        digits[i] = 0;
      }
    }
    digits = new int[digits.length + 1];
    Arrays.fill(digits, 0);
    digits[0] = 1;
    return digits;

  }
}
