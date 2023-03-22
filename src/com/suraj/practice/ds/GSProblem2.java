package com.suraj.practice.ds;

public class GSProblem2 {

  public static void main(String[] args) {
    System.out.println(new GSProblem2().secureChannel(2, "opp", "1234"));
  }

  private static final String ERROR_RETURN="-1";
  public String secureChannel(int operation, String message, String key) {
    if (operation == 1) {
      return encryptString(message, key);
    } else if (operation == 2) {
      return decryptString(message, key);
    } else {
      return ERROR_RETURN;
    }
  }

  private String decryptString(String message, String key) {
    if(key==null && message ==null){
      return null;
    }else if(key == null||message == null||message.length() == 0){
      return ERROR_RETURN;
    }
    StringBuilder sb = new StringBuilder();
    int startingIndex = 0;
    try {
      for (int i = 0; i < key.length(); i++) {
        int count;
        try{
           count = Integer.valueOf(key.substring(i, i + 1));
        }catch (Exception e){
          return ERROR_RETURN;
        }
        if(startingIndex==message.length()){
          break;
        }
        if (validEncryption(startingIndex, count, message)) {
          sb.append(message.charAt(startingIndex));
          startingIndex = startingIndex + count;
        } else {
          return ERROR_RETURN;
        }
      }
    } catch (Exception e) {
      return ERROR_RETURN;
    }
    if(startingIndex==message.length()){
      return sb.toString();
    }
    return sb.append(message.substring(startingIndex)).toString();
  }

  private boolean validEncryption(int startingIndex, int count, String message) {
    char character=message.charAt(startingIndex);
    for(int i=0;i<count;i++){
      if(character !=message.charAt(startingIndex+i)){
        return false;
      }
    }
    return true;
  }

  private String encryptString(String message, String key) {
    if(key==null && message ==null){
      return null;
    }else if(key == null||message == null||message.length() == 0){
      return ERROR_RETURN;
    }
    StringBuilder sb = new StringBuilder();
    int minLength = key.length() > message.length() ? message.length() : key.length();
    for (int i = 0; i < minLength; i++) {
      int count;
      try{
        count = Integer.valueOf(key.substring(i, i + 1));
      }catch (Exception e){
        return ERROR_RETURN;
      }
      char character = message.charAt(i);
      while (count > 0) {
        sb.append(character);
        count--;
      }
    }
    if (message.length() > minLength) {
      sb.append(message.substring(minLength));
    }
    return sb.toString();
  }

}
