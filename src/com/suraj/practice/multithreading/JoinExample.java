package com.suraj.practice.multithreading;

 class Runnable1 implements Runnable{

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName()+" start");
    try {
      Thread.sleep(10000l);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName()+" end");
  }
}
public class JoinExample{

  public static void main(String[] args) throws InterruptedException {
Thread t1=new Thread(new Runnable1(),"thread1");
    Thread t2=new Thread(new Runnable1(),"thread2");
    t1.start();
    t1.join(2000);
    t2.start();
  }

}


