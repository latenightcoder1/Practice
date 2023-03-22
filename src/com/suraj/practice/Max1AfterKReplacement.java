package com.suraj.practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Max1AfterKReplacement {

  public static void main(String[] args) {
    int[] array = new int[]{0, 0, 0, 1, 1, 1, 1, 0, 1};
    int k = 2;
    System.out.println(getMaxContinuous1AfterKReplacement(array, k));
    Set<String> set=new HashSet<>();
    set.add("hello");
    List<String> list=new ArrayList<>(set.stream().filter(a->a.equals("a")).collect(Collectors.toSet()));
    System.out.println(list);
  }

  private static int getMaxContinuous1AfterKReplacement(int[] array, int k) {
    int result=0;
    int count0=0;
    int low=0;
    for(int i=0;i<array.length;i++){
      if(array[i]==0){
        count0++;
      }
      while (count0>k){
        if(array[low]==0){
          count0--;
        }
        low++;
      }
      result=Math.max(result,i-low+1);
    }
    return result;
  }

}
