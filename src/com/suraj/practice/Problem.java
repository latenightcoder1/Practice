package com.suraj.practice;

import java.util.HashMap;
import java.util.Map;

public class Problem {

  public static void main(String[] args) {
    System.out.println(getIndices(new int[]{2,7,9,11},9));
  }

  //get pair of indices summing to target
  //getIndices(new int[]{2,7,9,11},9)
  private static String getIndices(int[] array,int target) {
    Map<Integer,Integer> map=new HashMap<>();
    for(int i=0;i<array.length;i++){
      map.put(array[i],i);
    }
    for(int i=0;i<array.length;i++){
      int remainingSum=target-array[i];
      if(map.get(remainingSum)!=null){
        return i+" "+map.get(remainingSum);
      }
    }
    return null;
  }

}
