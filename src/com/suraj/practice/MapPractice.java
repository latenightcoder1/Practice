package com.suraj.practice;

import java.util.HashMap;
import java.util.Map;
class Person1{
  Map<String,String> map;

  public Map<String,String> getMap(){
    return map;
  }

  public void setMap(Map<String,String> map){
     this.map=map;
  }

  public Person1 updatedPerson(Person1 person1,Map<String,String> map1){
    map1.forEach(person1.getMap()::putIfAbsent);
    return person1;
  }
}
public class MapPractice {

  public static void main(String[] args) {
    Person1 person1=new Person1();
    Map<String,String> map=new HashMap<>();
    map.put("a","A");
    map.put("b","B");
    person1.setMap(map);
    Map<String,String> copy=new HashMap<>();
    copy.put("c","C");
    copy.put("a","Aa");
    person1.updatedPerson(person1,copy);
    System.out.println(person1.getMap());
  }

}
