package com.suraj.practice.repractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RottenOrange {

  public int orangesRotting(int[][] grid) {
    int noOfMinutes = 0;

    int freshCount = 0;
    List<int[]> rottenIndexes = new ArrayList<>();
    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid[0].length; y++) {
        if (grid[x][y] == 2) {
          rottenIndexes.add(new int[]{x, y});
        } else if (grid[x][y] == 1) {
          freshCount = freshCount + 1;
        }
      }
    }

    if (freshCount == 0) {
      return noOfMinutes;
    }

    List<int[]> moves = Arrays.asList(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1},
        new int[]{0, -1});

    while (rottenIndexes.size() > 0) {
      List<int[]> temp = new ArrayList<>();

      for (int[] rottedOrange : rottenIndexes) {
        int x = rottedOrange[0];
        int y = rottedOrange[1];

        for (int[] move : moves) {
          if ((x + move[0] >= 0) && (x + move[0] < grid.length) && (y + move[1] >= 0)
              && (y + move[1] < grid[0].length) && grid[x + move[0]][y + move[1]] == 1) {
            temp.add(new int[]{x + move[0], y + move[1]});
            grid[x + move[0]][y + move[1]] = 2;
            freshCount = freshCount - 1;
          }
        }
      }
      rottenIndexes = temp;
      noOfMinutes = noOfMinutes + 1;
    }
    return freshCount != 0 ? -1 : noOfMinutes - 1;
  }

  public static void main(String[] args) {
    RottenOrange rottenOrange=new RottenOrange();
    int[][] grid={{1,2}};
    System.out.println(rottenOrange.orangesRotting(grid));
  }
}
