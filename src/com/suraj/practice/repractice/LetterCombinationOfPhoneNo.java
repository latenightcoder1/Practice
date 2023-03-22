package com.suraj.practice.repractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LetterCombinationOfPhoneNo {
  public List<String> letterCombinations(String digits) {
    List<String> result=new ArrayList<>();
    result.add("");
    Map<Character,List<Character>> lookup=new HashMap<>();
    lookup.put('2', Arrays.asList('a','b','c'));
    lookup.put('3',Arrays.asList('d','e','f'));
    lookup.put('4',Arrays.asList('g','h','i'));
    lookup.put('5',Arrays.asList('j','k','l'));
    lookup.put('6',Arrays.asList('m','n','o'));
    lookup.put('7',Arrays.asList('p','q','r'));
    lookup.put('8',Arrays.asList('s','t','u'));
    lookup.put('9',Arrays.asList('w','x','y','z'));

    for(char c:digits.toCharArray()){
      List<Character> list=lookup.get(c);
      Set<String> temp=new HashSet<>();
      for(String s:result){
        for(Character c1:list){
          temp.add(new StringBuilder().append(s).append(c1).toString());
        }
      }
      result=new ArrayList<>(temp);
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(new LetterCombinationOfPhoneNo().letterCombinations("23"));
  }
}
