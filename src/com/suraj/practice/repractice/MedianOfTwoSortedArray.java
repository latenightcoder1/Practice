package com.suraj.practice.repractice;

public class MedianOfTwoSortedArray {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int start1 = nums1.length - 1;
    int start2 = nums2.length - 1;
    int fillingIndex = nums1.length + nums2.length - 1;
    int medianIndex = (nums1.length + nums2.length) / 2;
    int medianValue = 0;
    while (fillingIndex >= medianIndex) {
      fillingIndex--;
      if (start1 == -1) {
        medianValue = nums2[start2];
        start2--;
      } else if (start2 == -1) {
        medianValue = nums1[start1];
        start1--;
      } else {
        if (nums1[start1] > nums2[start2]) {
          medianValue = nums1[start1];
          start1--;
        } else {
          medianValue = nums2[start2];
          start2--;
        }
      }
    }
    if ((nums1.length + nums2.length) % 2 != 0) {
      return medianValue * 1.0;
    } else {
      if (start1 == -1) {
        return (medianValue + nums2[start2]) / 2.0;
      } else if (start2 == -1) {
        return (medianValue + nums1[start1]) / 2.0;
      } else if (nums1[start1] > nums2[start2]) {
        return (medianValue + nums1[start1]) / 2.0;
      } else {
        return (medianValue + nums2[start2]) / 2.0;
      }
    }
  }
}
