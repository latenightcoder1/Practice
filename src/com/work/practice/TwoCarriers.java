package com.work.practice;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

public class TwoCarriers {

  private static Map<String, Set<String>> bnRnDetailsMap = new HashMap<>();
  private static Map<String,List<Integer>> bnIndexValues=new HashMap<>();
  private static Map<String,List<Integer>> rnIndexValues=new HashMap<>();


  public static void main(String[] args) {
    Map<String, List<Integer>> entries=getEntries();
    updateEntries(entries);
    Map<String, Entry<Integer,Integer>> frequencies=getFrequencies(Arrays.asList("rn1","bn1","rn2"));
    System.out.println(frequencies);
    updateEntries(Map.of("rn2:bn1", Arrays.asList(1,0,0,0,1,1,0),"rn3:bn1", Arrays.asList(1,0,0,0,0,1,0)));
     frequencies=getFrequencies(Arrays.asList("rn1","bn1","rn2"));
    System.out.println(frequencies);
    updateEntries(Map.of("rn8:bn2", Arrays.asList(1,0,0,0,1,1,0),
        "rn9:bn2", Arrays.asList(1,0,0,0,0,1,0),"bn2", Arrays.asList(0,0,0,0,1,1,0)
        ));
    frequencies=getFrequencies(Arrays.asList("rn1","bn1","rn2","bn2"));
    System.out.println(frequencies);
  }

  private static Map<String, Entry<Integer, Integer>> getFrequencies(List<String> devices) {
    Map<String,Entry<Integer,Integer>> result=new HashMap<>();
    for(final String device:devices){
      List<Integer> bnIndexValue = null;
      List<List<Integer>> sameBnRnsIndexValues=new ArrayList<>();
      if(bnRnDetailsMap.containsKey(device)){
        bnIndexValue=bnIndexValues.get(device);
        for(final String rn:bnRnDetailsMap.get(device)){
          sameBnRnsIndexValues.add(rnIndexValues.get(rn));
        }
      }else{
        for(Map.Entry<String,Set<String>> bnRnDetailsEntry:bnRnDetailsMap.entrySet()){
          if(bnRnDetailsEntry.getValue().contains(device)){
            bnIndexValue=bnIndexValues.get(bnRnDetailsEntry.getKey());
            for(final String rn:bnRnDetailsEntry.getValue()){
              sameBnRnsIndexValues.add(rnIndexValues.get(rn));
            }
            break;
          }
        }
      }
      result.put(device,calculateFrequencies(bnIndexValue,sameBnRnsIndexValues));
    }
return result;
  }

  private static Entry<Integer, Integer> calculateFrequencies(List<Integer> bnIndexValue, List<List<Integer>> sameBnRnsIndexValues) {
    PriorityQueue<Entry<Integer,Integer>> sumAgainstBand = new PriorityQueue<>((a,b)->b.getValue()- a.getValue());
    for(int i=0;i<bnIndexValue.size();i++){
      if(bnIndexValue.get(i)==0){
        sumAgainstBand.add(new AbstractMap.SimpleEntry(i, 0));
      }else{
        int finalI = i;
        int sum=sameBnRnsIndexValues.stream().map(a->a.get(finalI)).mapToInt(num-> num).sum();
        sumAgainstBand.add(new AbstractMap.SimpleEntry(i, sum));
      }
    }
    return new AbstractMap.SimpleEntry(sumAgainstBand.poll().getKey(),sumAgainstBand.poll().getKey());
  }

  private static Map<String, List<Integer>> getEntries() {
    Map<String,List<Integer>> entries=new HashMap<>();
    entries.put("rn1:bn1", Arrays.asList(1,0,1,0,1,1,0));
    entries.put("rn2:bn1", Arrays.asList(1,0,1,0,1,1,0));
    entries.put("rn3:bn1", Arrays.asList(1,0,1,0,0,1,0));
    entries.put("rn4:bn1", Arrays.asList(1,0,1,0,0,1,0));
    entries.put("bn1",     Arrays.asList(1,0,1,0,0,1,0));
    return entries;
  }

  private static void updateEntries(Map<String, List<Integer>> input) {
    //update rn bn details mapping
    for(final Map.Entry<String,List<Integer>> entry:input.entrySet()){
      String[] entities=entry.getKey().split(":");
      if(entities.length>1){
        Set<String> existingRns=bnRnDetailsMap.getOrDefault(entities[1],new HashSet<>());
        existingRns.add(entities[0]);
        bnRnDetailsMap.put(entities[1],existingRns);
        rnIndexValues.put(entities[0],entry.getValue());
      }else{
        bnIndexValues.put(entry.getKey(),entry.getValue());
      }

    }
  }

}
