package com.suraj.practice.ds;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ZolandoTrainingProblem {

  public static void main(String[] args) {
    System.out.println(solution(new String[]{"039","4","14","32","","34","7"}));
    System.out.println(solution(new String[]{"801234567","180234567","0","189234567","891234567","98","9"}));
    System.out.println(solution(new String[]{"5421","245","1452","0345","53","354"}));
  }

  private static int solution(String[] E) {
    Map<Integer, Set<Integer>> dayWiseInterest=getDayAndInterestedEmployees(E);

    int result=0;
    for(int i=0;i<10;i++){
      for (int j=i+1;j<10;j++){
        Set<Integer> combinedEmployess=new HashSet<>();
        combinedEmployess.addAll(dayWiseInterest.getOrDefault(i,new HashSet<>()));
        combinedEmployess.addAll(dayWiseInterest.getOrDefault(j,new HashSet<>()));
        if(combinedEmployess.size()>result){
          result=combinedEmployess.size();
        }
      }
    }
    return result;
  }

  private static Map<Integer, Set<Integer>> getDayAndInterestedEmployees(String[] strings) {
    Map<Integer, Set<Integer>> dayWiseInterest=new HashMap<>();
    for(int i=0;i<strings.length;i++){
      String preferredDays=strings[i];
      for(int j=0;j<preferredDays.length();j++){
        int day=Character.getNumericValue(preferredDays.charAt(j));
        Set<Integer> employees=dayWiseInterest.getOrDefault(day,new HashSet<>());
        employees.add(i);
        dayWiseInterest.put(day,employees);
      }
    }
    return dayWiseInterest;
  }

}
