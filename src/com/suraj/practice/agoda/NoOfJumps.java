package com.suraj.practice.agoda;

import java.util.HashMap;
import java.util.Map;

public class NoOfJumps {

  public static void main(String[] args) {
    System.out.println(getTotalNoOfWaysOfJumps(100));
  }

  //if one can jum
  private static int getTotalNoOfWaysOfJumps(int height) {
    Map<Integer,Integer> noOfWays=new HashMap<>();
    noOfWays.put(1,1);
    noOfWays.put(2,2);
    noOfWays.put(3,3);
    noOfWays.put(4,5);
    noOfWays.put(5,7);
    int i=6;
    while(i<=height){
      noOfWays.put(i,noOfWays.get(i-1)+noOfWays.get(i-2)
          +noOfWays.get(i-3)+noOfWays.get(i-4)+noOfWays.get(i-5));
      i++;
    }
    return noOfWays.get(height);
  }

}
