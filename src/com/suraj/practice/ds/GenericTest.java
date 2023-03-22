package com.suraj.practice.ds;

public class GenericTest {

  public static void main(String[] args) {
    GenericClass<String,String> person1=new GenericClass<>();
    person1.setAddress("ram nagar");
    person1.setSalary("bahut sara");
    person1.setName("suraj kr");

    GenericClass<Integer,String> person2=new GenericClass<>();
    person2.setAddress(1);
    person2.setName("megha manshwi");
    person2.setSalary("thoda km");

    GenericClass<Integer,Integer> person3=new GenericClass<>();

    GenericClass<String,Integer> person4=new GenericClass<>();
    NonGenericClass ng=new NonGenericClass();
  /*  ng.getSum(12,12);
    ng.getSum("ram","shyam");
    ng.getSum("ram",1);*/

  }

}
