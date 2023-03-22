package com.suraj.practice.multithreading;

public class Finally {

  private static int s=12;
  public static void main(String[] args) {
     doFinally();
    System.out.println(s);
  }

  private static void doFinally() {
    try{
      s=16;
    }finally {
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      s=24;
    }
  }

}
