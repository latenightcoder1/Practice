package com.suraj.practice.ds;

public class ProblemSolution {

  public static void main(String[] args) {
    System.out.println(new ProblemSolution().solution("A2Le", "2pL1"));
    System.out.println(new ProblemSolution().solution("a10", "10a"));
    System.out.println(new ProblemSolution().solution("ba1", "1Ad"));
    System.out.println(new ProblemSolution().solution("3x2x", "8"));
  }

  public boolean solution(String S, String T) {
    if ((S == null && T != null) || (T == null && S != null)) {
      return false;
    }
    if (S == null && T == null) {
      return true;
    }
    String convertedS = getConvertedString(S);
    String convertedT = getConvertedString(T);
    if (convertedS.length() != convertedT.length()) {
      return false;
    }
    for (int i = 0; i < convertedS.length(); i++) {
      if (!(convertedS.charAt(i) == '?' || convertedT.charAt(i) == '?'
          || (convertedS.charAt(i) == convertedT.charAt(i)))) {
        return false;
      }
    }
    return true;
  }

  private String getConvertedString(String s) {
    StringBuilder converted = new StringBuilder();
    int i = 0;
    while (i < s.length()) {
      if (Character.isLetter(s.charAt(i))) {
        converted.append(s.charAt(i));
        i++;
      } else if (Character.isDigit(s.charAt(i))) {
        int startingIndex = i;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
          i++;
        }
        int value = Integer.valueOf(s.substring(startingIndex, i));
        while (value > 0) {
          converted.append("?");
          value--;
        }
      }
    }
    return converted.toString();
  }

}
