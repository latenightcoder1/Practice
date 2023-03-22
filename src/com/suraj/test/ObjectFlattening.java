package com.suraj.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFlattening {

  public static void main(String[] args) {
    Map<String,Object> input=new HashMap<>();
    input.put("a","a");
    input.put("b","b");
    input.put("1",1);
    input.put("array",new int[]{1,2,3,5});
    input.put("list", Arrays.asList(1,2,3));
    Map<String,Object> firstLevelNesting=new HashMap<>();
    firstLevelNesting.put("list", Arrays.asList(1,2,3));
    firstLevelNesting.put("x","x");
    firstLevelNesting.put("6",6);
    Map<String,Object> secondLevelNesting=new HashMap<>();
    secondLevelNesting.put("p","p");
    secondLevelNesting.put("45",45);
    firstLevelNesting.put("secondMap",secondLevelNesting);
    input.put("map",firstLevelNesting);
    Map<String,Object> output=new HashMap<>();
    flattenObject(input,output,"");
    System.out.println(output);
    System.out.println(input);
  }

  private static void flattenObject(Map<String,Object> input,Map<String,Object> result,String prefix){
    for(Map.Entry<String,Object> entry:input.entrySet()){
      Object value=entry.getValue();
      if(value instanceof String || value instanceof Integer ||value instanceof List){
        result.put(prefix+entry.getKey(), entry.getValue());
      }else if(value instanceof HashMap){
        flattenObject((HashMap)value,result,prefix+entry.getKey()+".");
      }
    }
  }

}
