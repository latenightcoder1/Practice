package com.suraj.practice.ds;

import java.util.Arrays;
public class GSProblem3 {
  public static void main(String[] args) {
    System.out.println(getLeader(7,3));
  }

  // find out final leader if next third goes out of game
  //getLeader(7,3)
  private static int getLeader(int count, int pointer) {
   boolean[] flag=new boolean[count];
    Arrays.fill(flag,true);
    int currentPointer=1;
    int currentIndex=0;
    while (true && count>1){
      if(currentIndex== flag.length){
        currentIndex=0;
      }
      if(currentPointer==pointer&&flag[currentIndex]){
        flag[currentIndex]=false;
        count--;
        currentPointer=1;
      }else if(currentPointer<pointer&&flag[currentIndex]){
        currentPointer=currentPointer+1;
      }
      currentIndex=currentIndex+1;
    }
    for(int i=0;i< flag.length;i++){
      if(flag[i]){
        return i+1;
      }
    }
    return -1;
  }

}
