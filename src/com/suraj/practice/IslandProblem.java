package com.suraj.practice;

import java.util.ArrayDeque;
import java.util.Queue;

public class IslandProblem {

  public static void main(String[] args) {
    System.out.println(getNoOfIslands(new int[][]{{1,1,0,0,0},
        {1,1,0,0,0},
        {0,0,1,0,0},
        {0,0,0,1,1}}));

  }
  private static int getNoOfIslands(int[][] matrix){
    int noOfIsland=0;
    for(int i=0;i<matrix.length;i++){
      for(int j=0;j<matrix[0].length;j++){
        if(matrix[i][j]==1){
          noOfIsland++;
          traverseNext1s(matrix,i,j);
        }
      }
    }
    return noOfIsland;
  }

  private static void traverseNext1s(int[][] matrix, int x, int y) {
    Queue<int[]> next1s=new ArrayDeque<>();
    next1s.add(new int[]{x,y});
    while(!next1s.isEmpty()){
      int[] headElement=next1s.remove();
      int i=headElement[0];
      int j=headElement[1];
      matrix[i][j]=5;
      if(i+1<matrix.length && matrix[i+1][j]==1){
        next1s.add(new int[]{i+1,j});
      }
      if(i-1>=0 && matrix[i-1][j]==1){
        next1s.add(new int[]{i-1,j});
      }
      if(j-1>=0 && matrix[i][j-1]==1){
        next1s.add(new int[]{i,j-1});
      }
      if(j+1<matrix[0].length && matrix[i][j+1]==1){
        next1s.add(new int[]{i,j+1});
      }
    }
  }

}
