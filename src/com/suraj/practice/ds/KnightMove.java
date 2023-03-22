package com.suraj.practice.ds;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class KnightMove {

  public static void main(String[] args) {
//    System.out.println(minMoves(9, 4, 4, 4, 8));
//    System.out.println(minMoves(10, 0, 0, 4, 2));
    System.out.println(minMoves(6, 5, 1, 0, 5));
  }

  private static int minMoves(int n, int startRow, int startColumn, int endRow, int endColumn) {
    if (startColumn == endColumn && startRow == endRow) {
      return 0;
    }
    int[] row = {2, 2, -2, -2, 1, 1, -1, -1};
    int[] col = {-1, 1, 1, -1, 2, -2, 2, -2};
    Set<int[]> lookUp = new HashSet<>();
    Queue<int[]> moves = new ArrayDeque<>();
    moves.add(new int[]{startRow, startColumn});
    lookUp.add(new int[]{startRow, startColumn});
    int result=0;
    while (!moves.isEmpty()) {
      int size = moves.size();
      while (size > 0) {
        int[] position = moves.poll();
        for (int i = 0; i < row.length; i++) {
          int nextMoveRow = position[0] + row[i];
          int nextMoveColumn = position[1] + col[i];
          if(isNextMoveValid(nextMoveColumn,nextMoveRow,n)){
            if(nextMoveRow==endRow&&nextMoveColumn==endColumn){
              return result+1;
            }
            else if(!lookUp.contains(new int[]{nextMoveRow,nextMoveColumn})){
              lookUp.add(new int[]{nextMoveRow,nextMoveColumn});
              moves.add(new int[]{nextMoveRow,nextMoveColumn});
            }
          }
        }
        size--;
      }
      result++;
    }
    return -1;

  }

  private static boolean isNextMoveValid(int nextMoveColumn, int nextMoveRow,int n) {
   return ! (nextMoveRow<0||nextMoveRow>n-1||nextMoveColumn<0||nextMoveColumn>n-1);
  }

}
