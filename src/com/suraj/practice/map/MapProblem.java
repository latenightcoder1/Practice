package com.suraj.practice.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapProblem {

  public static void main(String[] args) {
    Map<String,Integer> map=new LinkedHashMap<>();
    map.put("a",1);
    map.put("x",2);
    map.put("b",1);
    map.put("x",3);

    for(Map.Entry<String,Integer> entry:map.entrySet()){
      System.out.println(entry.getKey()+" "+entry.getValue());
    }
  }

}
