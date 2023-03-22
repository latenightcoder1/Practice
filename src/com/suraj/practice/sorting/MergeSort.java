package com.suraj.practice.sorting;

public class MergeSort {

  public static void main(String[] args) {
    int[] array = new int[]{9, 3, 7, 5, 6, 4,4, 8, 2};
    mergeSort(array, 0, array.length - 1);
    for(int i:array){
      System.out.println(i);
    }
  }

  private static void mergeSort(int[] array, int low, int high) {
    if (low < high) {
      int mid = (low + high) / 2;
      mergeSort(array, low, mid);
      mergeSort(array, mid + 1, high);
      // merging two arrays- low to mid and mid+1 to high
      merge(array, low, mid, high);
    }
  }

  private static void merge(int[] array, int low, int mid, int high) {
    int[] left = new int[mid - low + 1];
    int[] right = new int[high - mid];
    for (int i = 0; i < left.length; i++) {
      left[i] = array[low + i];
    }
    for (int i = 0; i < right.length; i++) {
      right[i] = array[mid + 1 + i];
    }

    int mergingIndex = low;
    int first = 0;
    int second = 0;
    while (first < left.length || second < right.length) {
      if (first == left.length) {
        array[mergingIndex++] = right[second++];
      } else if (second == right.length) {
        array[mergingIndex++] = left[first++];
      } else if (left[first] <= right[second]) {
        array[mergingIndex++] = left[first++];
      } else {
        array[mergingIndex++] = right[second++];
      }
    }
  }


}
