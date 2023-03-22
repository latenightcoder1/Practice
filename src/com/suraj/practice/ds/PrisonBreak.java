package com.suraj.practice.ds;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PrisonBreak {

  public static void main(String[] args) {
    System.out.println(prison(3,2, Arrays.asList(1,2,3),Arrays.asList(1,2)));
    System.out.println(prison(2,2, Arrays.asList(1),Arrays.asList(2)));
    System.out.println(prison(6,6, Arrays.asList(4),Arrays.asList(2)));
  }
  public static long prison(int n, int m, List<Integer> h, List<Integer> v) {
    Set<Integer> rows = new TreeSet<>();
    Set<Integer> columns = new TreeSet<>();
    for (int i = 1; i <= n + 1; i++) {
      rows.add(i);
    }
    for (int j = 1; j <= m + 1; j++) {
      columns.add(j);
    }
    for (Integer hRemoved : h) {
      rows.remove(hRemoved);
    }
    for (Integer vRemoved : v) {
      columns.remove(vRemoved);
    }
    long maxWidth = 0;
    int previous = 0;
    for (Integer availableRow : rows) {
      if ((availableRow - previous) > maxWidth) {
        maxWidth = availableRow - previous;
      }
      previous = availableRow;
    }

    long maxLength = 0;
    previous = 0;
    for (Integer availableColumn : columns) {
      if ((availableColumn - previous) > maxLength) {
        maxLength = availableColumn - previous;
      }
      previous = availableColumn;
    }
    return maxLength * maxWidth;
  }
}
