package com.suraj.practice.dp;

public class InterleavedString {

  public static void main(String[] args) {
    System.out.println(new InterleavedString()
        .isInterLeave("AAAB",
            "AAAC",
            "AAACAAAB"));
  }

  public boolean isInterLeave(String a, String b, String c) {
    if (a.length() + b.length() != c.length()) {
      return false;
    }
    int firstIndex = 0;
    int secondIndex = 0;
    int thirdIndex = 0;
    while (firstIndex < a.length() || secondIndex < b.length()) {
      if (firstIndex == a.length()) {
        if (b.charAt(secondIndex) != c.charAt(thirdIndex)) {
          return false;
        }
        secondIndex++;
      } else if (secondIndex == b.length()) {
        if (a.charAt(firstIndex) != c.charAt(thirdIndex)) {
          return false;
        }
        firstIndex++;
      } else {
        if (a.charAt(firstIndex) == c.charAt(thirdIndex)) {
          firstIndex++;
        } else if (b.charAt(secondIndex) == c.charAt(thirdIndex)) {
          secondIndex++;
        } else {
          return false;
        }
      }
      thirdIndex++;
    }
    return true;
  }

}
