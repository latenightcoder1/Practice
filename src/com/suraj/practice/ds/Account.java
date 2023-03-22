/*
package com.suraj.practice.ds;

public class Account implements Comparable<Account>{

  int noOfRegularMovies,noOfExclusiveMovies;
  String ownerName;

  //1)
  public Account(String ownerName,int noOfRegularMovies,int noOfExclusiveMovies){
    this.ownerName=ownerName;
    this.noOfRegularMovies=noOfRegularMovies;
    this.noOfExclusiveMovies=noOfExclusiveMovies;
  }

  //2)
  public int monthlyCost(){
    return basePrice+(noOfExclusiveMovies*exclusiveMoviePrice)+(noOfRegularMovies*regularMoviePrice);
  }

  //3)
  @Override
  public int compareTo(Account o) {
    return this.monthlyCost()-o.monthlyCost();
  }

  //4)
  @Override
  public String toString() {
    return "Owner is "+ownerName+" and monthly cost is "+monthlyCost()+" USD.";
  }


}
*/
