package com.work.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution1 {

  public static void main(String[] args) {
    int sum=solution(new int[]{130,191,200,10});
    System.out.println(sum);
    int sum1=solution(new int[]{400,45,300,300});
    System.out.println(sum1);
    int sum2=solution(new int[]{50,222,49,52,25});
    System.out.println(sum2);
    int sum3=solution(new int[]{30,909,3190,99,3990,9009});
    System.out.println(sum3);
  }

  public static int solution(int[] A){
    final Map<String, PriorityQueue<Integer>> noGroupedByFirstAndLastDigit=new HashMap<>();
    for(final int i:A){
      final int firstDigit=i/((int)(Math.pow(10,(int)Math.log10(i))));
      final int lastDigit=i%10;
      final String key=firstDigit+"/"+lastDigit;
      final PriorityQueue<Integer> values=noGroupedByFirstAndLastDigit.getOrDefault(key,new PriorityQueue<>((a,b)->b-a));
      values.add(i);
      noGroupedByFirstAndLastDigit.put(key,values);
    }
    if(A.length==noGroupedByFirstAndLastDigit.size()){
      return -1;
    }
    int sum=Integer.MIN_VALUE;
    for(final Map.Entry<String,PriorityQueue<Integer>> entry:noGroupedByFirstAndLastDigit.entrySet()){
      final PriorityQueue<Integer> groupedNos=entry.getValue();
      if(groupedNos.size()>1){
        final int currentSum=groupedNos.poll()+groupedNos.poll();
        if(currentSum>sum){
          sum=currentSum;
        }
      }
    }
    return sum;
  }

}