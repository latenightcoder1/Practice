package com.suraj.practice.ds;

public class DoubleExample {

  public static void main(String[] args) {
    Float f=32.54f;

    processObject(f);



  }

  private static void processObject(Object f) {
    Double d= ((Float)f).doubleValue();
    Double d1=(Double)null;
    System.out.println(d);
    System.out.println(d1.equals(null));
    if(d.equals(32.54d)){
      System.out.println("hello");
    }
  }

}
