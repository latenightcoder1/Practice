package com.suraj.practice.graph;

public class MaxArea {

  public static void main(String[] args) {
    int[][] grid = new int[][]{{1, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
    System.out.println(getMaxArea(grid));
  }

  private static int getMaxArea(int[][] grid) {
    int result = 0;
    int[][] movments = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0},
        {1, 1}};
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          int maxValue = 1;
          for (int[] mov : movments) {
            if (i + mov[0] < 0 || i + mov[0] >= grid.length || j + mov[1] < 0 ||
                j + mov[1] >= grid[0].length || grid[i + mov[0]][j + mov[1]] == 0) {
              continue;
            }
            maxValue = Math.max(maxValue, grid[i + mov[0]][j + mov[1]]);
          }
          grid[i][j] = maxValue + 1;
        }
        result = Math.max(result, grid[i][j]);
      }
    }
    return result-1;
  }

}
