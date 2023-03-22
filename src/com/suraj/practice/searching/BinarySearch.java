package com.suraj.practice.searching;

public class BinarySearch {

  public static void main(String[] args) {
    int[] array = new int[]{1, 2, 3, 3, 4, 5};
    System.out.println(binarySearch(array, 0, array.length - 1, 7));
    System.out.println(binarySearchRecursive(array, 0, array.length - 1, 7));
  }

  private static int binarySearchRecursive(int[] array, int low, int high, int x) {
    if (low <= high) {
      int mid = low + (high - low) / 2;
      if (array[mid] == x) {
        return mid;
      }
      if (array[mid] > x) {
        return binarySearchRecursive(array, low, mid - 1, x);
      } else {
        return binarySearchRecursive(array, mid + 1, high, x);
      }
    }
    return -1;
  }

  private static int binarySearch(int[] array, int low, int high, int x) {
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (array[mid] == x) {
        return mid;
      }
      if (array[mid] > x) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }

}
