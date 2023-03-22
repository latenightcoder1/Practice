package com.suraj.practice.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Person {

  int age;
  String name;

  public Person(int age, String name) {
    this.age = age;
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Person{" +
        "age=" + age +
        ", name='" + name + '\'' +
        '}';
  }
}

public class Sorting {

  public static void main(String[] args) {
    List<Person> persons = Arrays.asList(new Person(1, "ram"), new Person(2, "shyam"),
        new Person(3, "ghanshyam"), new Person(4, "suraj"),new Person(5, "suraj"));
    List<Person> sortedList=persons.stream().sorted((a,b)->a.age-b.age).collect(Collectors.toList());
    List<Person> sortedList1=persons.stream().sorted(Comparator.comparingInt(a -> a.age)).collect(Collectors.toList());
    List<Person> sortedList2=persons.stream().sorted(Comparator.comparing(Person::getName).thenComparing(Person::getAge).reversed()).collect(Collectors.toList());
    System.out.println(sortedList2);
  }

}
