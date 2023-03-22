package com.suraj.practice.backtracking;

import java.util.ArrayList;

public class SudokuSolver {

  public static void main(String[] args) {
    ArrayList<ArrayList<Character>> a=get();
    new SudokuSolver().solveSudoku(a);
    System.out.println(a);
  }

  static ArrayList<ArrayList<Character>> get() {
    String[] array = new String[]{"53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1",
        "7...2...6", ".6....28.", "...419..5", "....8..79"};
    ArrayList<ArrayList<Character>> a = new ArrayList<>();
    for(String s:array){
      ArrayList<Character> chars=new ArrayList<>();
      for(Character c:s.toCharArray()){
        chars.add(c);
      }
      a.add(chars);
    }
    return a;
  }

  public void solveSudoku(ArrayList<ArrayList<Character>> a) {
    ArrayList<ArrayList<ArrayList<Character>>> result=new ArrayList<>();
    solveSudokuUtil(a, 0, 0,result);
    a= result.get(0);
  }

  private void solveSudokuUtil(ArrayList<ArrayList<Character>> a, int i, int j,ArrayList<ArrayList<ArrayList<Character>>> result) {
    if (i == a.size()) {
      result.add(new ArrayList<>(a));
      return;
    }
    int nexti = 0;
    int nextj = 0;
    if (j == a.get(0).size() - 1) {
      nextj = 0;
      nexti = i + 1;
    } else {
      nextj = j + 1;
      nexti = i;
    }

    if (a.get(i).get(j) != '.') {
      solveSudokuUtil(a, nexti, nextj,result);
    } else {
      for (int m = 1; m <= 9; m++) {
        char c = (char) (m + '0');
        if (isValid(a, i, j, c)) {
          a.get(i).set(j, c);
          solveSudokuUtil(a, nexti, nextj,result);
          a.get(i).set(j, '.');
        }
      }
    }
  }

  private boolean isValid(ArrayList<ArrayList<Character>> a, int i, int j, char c) {
    for (int column = 0; column < a.size(); column++) {
      if (a.get(i).get(column) == c) {
        return false;
      }
    }
    for (int row = 0; row < a.size(); row++) {
      if (a.get(row).get(j) == c) {
        return false;
      }
    }
    int smi = (i / 3) * 3;
    int smj = (j / 3) * 3;
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        if (a.get(smi + x).get(smj + y) == c) {
          return false;
        }
      }
    }
    return true;
  }

}
