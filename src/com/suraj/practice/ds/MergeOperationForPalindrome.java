package com.suraj.practice.ds;

public class MergeOperationForPalindrome {

  public static void main(String[] args) {
    String s=null;
    System.out.println("Suraj".equals(null));
//    System.out.println(s.equals("Suraj"));
    int count1=minMergeOperationForPalindrome(new int[]{6, 1, 3, 7});
    int count2=minMergeOperationForPalindrome(new int[]{6, 1, 4, 3, 1, 7});
    int count3=minMergeOperationForPalindrome(new int[]{1, 3, 3, 1});
    System.out.println(count1+" "+count2+" "+count3);
  }

  //min merge operation for palindrome
  // int count3=minMergeOperationForPalindrome(new int[]{1, 3, 3, 1});
  private static int minMergeOperationForPalindrome(int[] array) {
    int i=0;
    int j=array.length-1;
    int result=0;
    while(i<j){
      if(array[i]<array[j]){
        array[i+1]=array[i]+array[i+1];
        i++;
        result++;
      }else if(array[j]<array[i]){
        array[j-1]=array[j]+array[j-1];
        j--;
        result++;
      }else{
        i++;
        j--;
      }
    }
    return result;
  }

}
