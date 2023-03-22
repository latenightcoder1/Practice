package com.suraj.practice.stream;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreaming {

  public static void main(String[] args) {
    List<Integer> list=new ArrayList<>();
    for(int i=1;i<10;i++){
      list.add(i);
    }
    long time=System.currentTimeMillis();
    int sum1=list.parallelStream().reduce(1,(a,b)->a*b);
    System.out.println("sum1 "+sum1);
    System.out.println(System.currentTimeMillis()-time);
    long time1=System.currentTimeMillis();
    int sum2=list.stream().reduce(1,(a,b)->a*b);
    System.out.println("sum2 "+sum2);
    System.out.println(System.currentTimeMillis()-time1);
  }

}
