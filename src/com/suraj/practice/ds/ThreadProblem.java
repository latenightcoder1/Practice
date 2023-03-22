package com.suraj.practice.ds;

public class ThreadProblem extends Thread{
  public void run(){
    System.out.println("x");
  }

  public static void main(String[] args) {
    ThreadProblem t=new ThreadProblem();
    t.setDaemon(false);
    t.start();
    t.setDaemon(true);
  }

}
