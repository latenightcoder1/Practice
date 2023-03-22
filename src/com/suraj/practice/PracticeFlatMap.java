package com.suraj.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Attribute{
    String name;
    int age;

    public Attribute(String name,int age ){
        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "com.suraj.practice.Attribute{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) o;
        return age == attribute.age && Objects.equals(name, attribute.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
public class PracticeFlatMap {
    public static void main(String[] args) {
        List<Attribute> as=new ArrayList<>();
       updateAttribute(as);
       System.out.println(as);
    }

    private static void updateAttribute(List<Attribute> as) {
        as.add(new Attribute("name",6));
    }
}
