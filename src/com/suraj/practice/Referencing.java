package com.suraj.practice;

public class Referencing {
  private String name;
  private String age;
  private String address;

  public Referencing(String name, String age, String address) {
    this.name = name;
    this.age = age;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "com.suraj.practice.Referencing{" +
        "name='" + name + '\'' +
        ", age='" + age + '\'' +
        ", address='" + address + '\'' +
        '}';
  }

  public static void main(String[] args) {
    Referencing referencing=new Referencing("a","b","c");
    Referencing referencing1=referencing;
    System.out.println(referencing1);
    System.out.println(referencing);
    referencing.setAddress("x");
    System.out.println(referencing1);
    System.out.println(referencing);
    referencing1.setAge("4");
    System.out.println(referencing1);
    System.out.println(referencing);
  }
}
