package com.suraj.practice.backtracking;

import java.util.ArrayList;

public class Permuations {

  public static void main(String[] args) {
    ArrayList<Integer> list=new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    System.out.println(new Permuations().permute(list));
  }
  public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
    ArrayList<ArrayList<Integer>> result=new ArrayList<>();
    for(int i=0;i<A.size();i++){
      for(int j=i;j<A.size();j++){
        ArrayList<Integer> entry=new ArrayList<>();
        for(int k=0;k<A.size();k++){
          entry.add(A.get(k));
        }
        entry.remove(i);
        entry.add(j,A.get(i));
        result.add(entry);
      }
    }
    return result;
  }

}
