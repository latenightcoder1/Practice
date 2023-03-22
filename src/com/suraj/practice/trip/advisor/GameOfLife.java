package com.suraj.practice.trip.advisor;

import java.util.Arrays;

/**
 * 1 surrounded by less than 2 1's >> 0
 * 1 surrounded by 2 or 3 1's >> 1
 * 1 surrounded by more than 3 1's >> 0
 * 0 surrounded by 3 1's >> 1
 */
public class GameOfLife {

  public static void main(String[] args) {
    int[][] input = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
    gameOfLife(input);
    int[][] expectedOutput = new int[][]{{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}};
    System.out.println(Arrays.deepEquals(input,expectedOutput));
  }

  public static void gameOfLife(int[][] board) {
    // indicative replacement i.e. set such values which are not known to matrix
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        applyLogic(i, j, board);
      }
    }

    //final replacement
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] <= 0) {
          board[i][j] = 0;
        } else {
          board[i][j] = 1;
        }
      }
    }

  }

  private static void applyLogic(int i, int j, int[][] board) {
    int[] dx = new int[]{0, 0, -1, 1, -1, -1, 1, 1};
    int[] dy = new int[]{-1, 1, 0, 0, -1, 1, -1, 1};
    int oneCount = 0;
    for (int k = 0; k < 8; k++) {
      if ((i + dx[k]) >= board.length || (i + dx[k]) < 0 || (j + dy[k]) >= board[0].length
          || (j + dy[k]) < 0) {
        continue;
      } else if ((board[i + dx[k]][j + dy[k]] == 1) || (board[i + dx[k]][j + dy[k]] == -1)) {
        oneCount++;
      }
    }
    if ((oneCount == 3) && (board[i][j] == 0)) {
      board[i][j] = 2;
    }
    if ((oneCount < 2 || oneCount > 3) && (board[i][j] == 1)) {
      board[i][j] = -1;
    }
  }

}
