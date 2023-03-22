package com.suraj.practice;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Person {

  private String name;
  private Integer age;
  private String address;

  public Person(String name, int age, String address) {
    this.name = name;
    this.age = age;
    this.address = address;
  }

  public Person() {
  }

  @Override
  public String toString() {
    return "com.suraj.practice.Person{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", address='" + address + '\'' +
        '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public static void main(String[] args) {
    Set<Person> existingRoles = new TreeSet<>(Comparator.comparingInt(Person::getAge));
    Set<Person> updatedRoles = new TreeSet<>(Comparator.comparingInt(Person::getAge));
    Set<Person> set1=new HashSet<>();
    set1.add(new Person("ram",5,"2"));
    set1.add(new Person("shyam",4,"5"));
    set1.add(new Person("ram",5,"3"));
    set1.add(new Person("shyam",4,"6"));
    Set<Person> set2=new HashSet<>();
    set2.add(new Person("shyam",4,"5"));
    set2.add(new Person("ram",5,"2"));
    set2.add(new Person("shyam",4,"6"));
    set2.add(new Person("ram",6,"3"));
    existingRoles.addAll(set1);
    updatedRoles.addAll(set2);
    if(existingRoles.equals(updatedRoles)){
      System.out.println("both equal");
    }else{
      System.out.println("not equal");
    }
    Person person=new Person();
    System.out.println(String.valueOf(person.getAddress()).equals("null"));

    System.out.println("hello world"+"\n"+"hi hello");
  }
}
