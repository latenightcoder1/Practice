package com.suraj.practice.abc;
class Student{
  private Long long1;
  private String name;

  public Student(Long long1, String name) {
    this.long1 = long1;
    this.name = name;
  }

  public Long getLong1() {
    return long1;
  }

  public String getName() {
    return name;
  }
}
public class LongComparison {

  public static void main(String[] args) {
    Student student1=new Student(123l,"suraj kumar");
    Student student2=new Student(2345l,"bablu kumar");
    System.out.println(student1.getLong1()>student2.getLong1());
    System.out.println(student1.getLong1()<student2.getLong1());
    System.out.println(student1.getLong1()<124);
  }

}
