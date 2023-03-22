package com.suraj.practice.multithreading;

import java.util.Arrays;
import java.util.List;

public class ThreadSleepInFinally {

  public static void main(String[] args) {
   List<String> list=Arrays.asList("Ram");
   if(list.contains(null)){
     System.out.println("hello5");
   }
    playWithinFinally();
  }

  private static void playWithinFinally() {
    try{
      System.out.println("hello");
    }finally {
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("hello 2");
    }
  }

}
