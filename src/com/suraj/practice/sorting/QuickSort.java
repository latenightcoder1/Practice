package com.suraj.practice.sorting;

public class QuickSort {

  public static void main(String[] args) {
    int[] array = new int[]{9, 3, 7, 5, 6, 4, 4, 8, 2, 1};
    quickSort(array, 0, array.length - 1);
    for (int i : array) {
      System.out.println(i);
    }
  }

  private static void quickSort(int[] array, int low, int high) {
    if (low < high) {
      int partitionIndex = partition(array, low, high);
      quickSort(array, low, partitionIndex - 1);
      quickSort(array, partitionIndex + 1, high);
    }
  }

  private static int partition(int[] array, int low, int high) {
    int i = low, j = high;
    int pivot = array[low];
    while (i < j) {
      while (i <= high && array[i] <= pivot) {
        i++;
      }
      while (j >= low && array[j] > pivot) {
        j--;
      }
      if (i < j) {
        swap(array, i, j);
      }
    }
    swap(array, low, j);
    return j;
  }

  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

}
