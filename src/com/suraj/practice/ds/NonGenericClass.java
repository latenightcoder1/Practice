package com.suraj.practice.ds;

public class NonGenericClass {

  public <X,Y> String getSum(X first,Y second,Integer third){
    if(first instanceof Integer){
      return String.valueOf((Integer) first+(Integer) second);
    }
    else{
      return new StringBuilder().append(first).append(second).toString();
    }
  }

}
