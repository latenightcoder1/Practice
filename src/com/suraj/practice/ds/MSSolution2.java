package com.suraj.practice.ds;

public class MSSolution2 {

  public static void main(String[] args) {
    System.out.println(brushCount(new int[]{5,8}));
    System.out.println(brushCount(new int[]{1,3,2,1,2,1,5,3,3,4,2}));
    System.out.println(brushCount(new int[]{1,1,1,1}));
  }

  //total no of brush count to paint the wall if one brush can paint one width
  //brushCount(new int[]{1,3,2,1,2,1,5,3,3,4,2})
  private static int brushCount(int[] A) {
    int brushCount=0;
    int prevHeight = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] > prevHeight) {
        brushCount =brushCount+ A[i] - prevHeight;
      }
      prevHeight = A[i];
    }
    return brushCount;
  }

}
