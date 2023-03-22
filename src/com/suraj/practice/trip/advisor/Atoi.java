package com.suraj.practice.trip.advisor;

public class Atoi {

  public static void main(String[] args) {
    System.out.println(myAtoi("2147483648"));
    System.out.println(myAtoi("   -42"));
  }

  //myAtoi("2147483648") >> 2147483647
  //myAtoi("   -42") >> -42
  public static int myAtoi(String s) {
    int result = 0;
    if (s == null) {
      return result;
    }
    int i = 0;
    while (s.charAt(i) == ' ') {
      i++;
    }
    boolean isNegative = s.charAt(i) == '-';
    if (s.charAt(i) == '-' || s.charAt(i) == '+') {
      i = i + 1;
    }
    while (i < s.length()) {
      int value = s.charAt(i) - 48;
      if (value < 0 || value > 9) {
        break;
      }
      if (result > Integer.MAX_VALUE / 10 || ((Integer.MAX_VALUE - result * 10) < value)) {
        return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      }
      result = (result * 10) + value;
      i++;
    }
    return isNegative ? -result : result;
  }

}
