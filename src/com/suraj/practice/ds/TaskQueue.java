package com.suraj.practice.ds;

import java.util.Arrays;
import java.util.List;

public class TaskQueue {

  public static void main(String[] args) {
    System.out.println(minTime(Arrays.asList(3, 2, 5, 7), Arrays.asList(5, 4, 10, 12),
        Arrays.asList(10, 6, 10, 5)));
    System.out.println(minTime(Arrays.asList(4,3),Arrays.asList(6,5),Arrays.asList(8,8)));
    System.out.println(minTime(Arrays.asList(1),Arrays.asList(25),Arrays.asList(40)));
  }

  public static long minTime(List<Integer> batchSize, List<Integer> processingTime,
      List<Integer> numTasks) {
    long minTime = 0;
    for (int i = 0; i < batchSize.size(); i++) {
      int batch = batchSize.get(i);
      int perBatchTime = processingTime.get(i);
      int totalTasks = numTasks.get(i);
      int unit=totalTasks / batch;
      if(totalTasks % batch != 0){
        unit++;
      }
      long totalTime=(long)unit * (long)perBatchTime;
      if (totalTime > minTime) {
        minTime = totalTime;
      }
    }
    return minTime;
  }

}
