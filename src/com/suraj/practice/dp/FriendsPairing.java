package com.suraj.practice.dp;

public class FriendsPairing {

  public static void main(String[] args) {
    System.out.println(new FriendsPairing().countFriendsPairings(6569));

  }
  public long countFriendsPairings(int n)
  {
    if(n<=1){
      return n;
    }
    //if current remains single n-1 other will solve for thmeselves
    // if it pairs with some one - so no of pair (n-1) and remaining n-2 will pair them
    // so f(n)=f(n-1)+(n-1)f(n-2);
    long lastPairing=2;// pairing combination of friends are 2
    long lastToLastPairing=1;
    for(int i=3;i<=n;i++){
      long current=lastPairing+(i-1)*lastToLastPairing;
      lastToLastPairing=lastPairing;
      lastPairing=current;
    }
    return lastPairing;
  }
}
