package com.work.practice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Solution2 {

  public static void main(String[] args) {
    int[] array1=new int[10000000];
    int[] array2=new int[10000000];
    for(int i=0;i<10000000;i++){
      array1[i]=i;
      array2[i]=i;
    }
    long startTime=System.currentTimeMillis();
    for(int i:array1){

    }
    System.out.println("first iteration "+(System.currentTimeMillis()-startTime));
     startTime=System.currentTimeMillis();
    for(final int i:array2){

    }
    System.out.println("second iteration "+(System.currentTimeMillis()-startTime));
   /* String S = new StringBuilder().append("admin  -wx 29 Sep 1983        833 source.h").append("\n")
        .append("admin  r-x 23 Jun 2003     854016 blockbuster.mpeg").toString();
    System.out.println(solution(S));*/
  }

  private static String solution(String S) {
    final String[] lines = S.split("\n");
    System.out.println("files details are");
    for(String a:lines){
      System.out.println(a);
    }
    final List<String[]> allKeywords = new ArrayList<>();
    for (final String line : lines) {

      final String[] fileDetails = {line.substring(0,6).trim(), line.substring(7,10).trim(),
          line.substring(11,22).trim(), line.substring(23,33).trim(),line.substring(34).trim() };
      allKeywords.add(fileDetails);
    }
    System.out.println("files details are");
    for(String[] data:allKeywords){
      System.out.println(data[0]+"/"+data[1]+"/"+data[2]+"/"+data[3]+"/"+data[4]);
    }
    final Optional<Date> earliestDateOptional = allKeywords.stream()
        .filter(file -> file[0].equals("admin")
            && file[1].contains("x") && Integer.parseInt(file[3]) < 14 * Math.pow(2, 20))
        .map(file -> new Date(file[2])).min(Date::compareTo);
    return earliestDateOptional.map(date->new SimpleDateFormat("dd MMM yyyy").format(date)).orElse(null);
  }

}
