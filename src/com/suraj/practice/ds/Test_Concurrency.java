package com.suraj.practice.ds;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Test_Concurrency {

  public static void main(String[] args) throws InterruptedException {
    BlockingDeque<Integer> deque=new LinkedBlockingDeque<>();
    deque.offerLast(10,5, TimeUnit.SECONDS);
    System.out.println(deque.pollLast(5,TimeUnit.SECONDS)+"");
    System.out.println(deque.pollFirst(5,TimeUnit.SECONDS)+"");
  }

}
