package com.suraj.practice.ds;

public class GenericClass<X, Y> {

  private X address;
  private Y salary;
  private String name;

  public X getAddress() {
    return address;
  }

  public void setAddress(X address) {
    this.address = address;
  }

  public Y getSalary() {
    return salary;
  }

  public void setSalary(Y salary) {
    this.salary = salary;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public <X,Y> String processName(X address,Y salary){
    return "";
  }
}
