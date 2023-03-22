package com.suraj.practice.ds;

public class JumperProblem {

  public static void main(String[] args) {
    System.out.println(jump(8, 3));
  }

  private static int jump(int flagHeight, int bigJump) {
    return (flagHeight / bigJump) + (flagHeight % bigJump);
  }

}
