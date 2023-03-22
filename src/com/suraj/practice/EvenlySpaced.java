package com.suraj.practice;

public class EvenlySpaced {

  public boolean evenlySpaced(int a, int b, int c) {
    int low;
    int mid;
    int large;
    if (a > b) {
      if (b > c) {
        mid = b;
        large = a;
        low = c;
      } else if (a > c) {
        mid = c;
        large = a;
        low = b;
      } else {
        mid = a;
        low = b;
        large = c;
      }
    } else {
      if (a > c) {
        mid = a;
        large = b;
        low = c;
      } else if (b > c) {
        mid = c;
        large = b;
        low = a;
      } else {
        mid = b;
        low = a;
        large = c;
      }
    }

    return (large - mid) == (mid - low);

  }
}
