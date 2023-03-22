package com.suraj.practice.ds;

import java.util.Optional;

class Employee1 {

}
class Accountant1 extends Employee1{

}

class Employee2 {
  public Employee2(int i){

  }

}
class Accountant2 extends Employee2{

  public Accountant2(int i) {
    super(i);
  }
}
public class GenericTest2{


  public static void main(String[] args) {
    Optional<String> ops=Optional.empty();
  }
}
