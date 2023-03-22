package com.suraj.practice.dp;

import java.util.HashMap;

public class NoOfPathsWithASum {

  public static void main(String[] args) {
      int[][] mat ={{4, 7, 1, 6},{5, 7, 3, 9},{3, 2, 1, 2},{7, 1, 6, 3}};
      int cost = 25;
      System.out.println(countPaths(mat, cost));
  }

  private static int countPaths(int[][] mat, int cost) {
    if (mat == null && mat.length == 0) {
      return 0;
    }
    return  countPaths(mat,0,0,cost,new HashMap<>());
  }

  private static int countPaths(int[][] mat, int i, int j, int cost, HashMap<String, Integer> lookup) {
    if(i>mat.length-1||j> mat[0].length){
      return 0;
    }
    if(i==mat.length-1&&j== mat[0].length-1){
      return (cost-mat[i][j])==0?1:0;
    }
    String key=i+"*"+j+"*"+cost;
    if(!lookup.containsKey(key)){
      int value=0;
      if(i==mat.length-1){
        value=countPaths(mat,i,j+1,cost-mat[i][j],lookup);
      }else if(j==mat[0].length-1){
        value=countPaths(mat,i+1,j,cost-mat[i][j],lookup);
      }else{
        value=countPaths(mat,i,j+1,cost-mat[i][j],lookup)+countPaths(mat,i+1,j,cost-mat[i][j],lookup);
      }
      lookup.put(key,value);
    }
    return lookup.get(key);
  }

}
