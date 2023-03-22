package com.suraj.practice.multithreading;

public class TwoThreadAlternatelyPrintingNo {

  static volatile int counter = 1;

  public static void main(String[] args) {

    Thread t1 = new Thread(() -> {
      for (int i = 1; i <= 100; i++) {
        new TwoThreadAlternatelyPrintingNo().printOdd(i);
      }
    }, "thread1");
    Thread t2 = new Thread(() -> {
      for (int i = 1; i <= 100; i++) {
        new TwoThreadAlternatelyPrintingNo().printEven(i);
      }
    }, "thread2");
    t1.start();
    t2.start();
  }

  private void printEven(int i) {
    synchronized (this) {
      if (i % 2 != 0) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println(Thread.currentThread().getName() + " >> " + i);
      notifyAll();
    }
  }

  private void printOdd(int i) {
    synchronized (this) {
      if (i % 2 == 0) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println(Thread.currentThread().getName() + " >> " + i);
      notifyAll();
    }
  }

}
