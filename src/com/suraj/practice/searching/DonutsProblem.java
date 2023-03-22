package com.suraj.practice.searching;

public class DonutsProblem {

  public static void main(String[] args) {
    System.out.println(findMinTime(10,4,new int[]{1, 2, 3, 4}));
  }
  public static int findMinTime(int n, int l, int[] arr) {
    int start=0;
    //int maxtime >> let's say we have one chef with rank 8 and he got 1000 donuts order
    //8+16+24+....>> 8(1000)(1001)/2=>>400000
    int end=4000000;

    return minTime(n,arr,start,end);
  }

  private static int minTime(int n,int[] arr,int start,int end){
    while(start<=end){
      int mid=start+(end-start)/2;
      int noOfDonuts=getDonuts(arr,mid);
      if(noOfDonuts>=n){
        end=mid-1;
      }else{
        start=mid+1;
      }
    }
    return start;
  }

  private static int getDonuts(int[] arr,int time){
    int donuts=0;
    for(int i=0;i<arr.length;i++){
      int sum=0;
      int duration=arr[i];
      while(sum+duration<=time){
        donuts++;
        sum=sum+duration;
        duration=duration+arr[i];
      }
    }
    return donuts;
  }
}
