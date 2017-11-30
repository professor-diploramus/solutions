package org.pdiploramus;

import java.util.Arrays;

/*
https://discuss.leetcode.com/topic/36805/c-16ms-fastest-beats-97
 */
public class CreateMaximumNumber {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] max = new int[k];
        Arrays.fill(max, 0);
        for (int i = 0; i <= k; i++) {
            int len1 = i;
            int len2 = k - i;
            if (len1 > nums1.length || len2 > nums2.length) continue;
            int[] maxNum1 = getMaxNum(nums1, len1);
            int[] maxNum2 = getMaxNum(nums2, len2);
            int[] merged = merge(maxNum1, maxNum2);
            max = getMax(max, merged);
        }
        return max;
    }

    private int[] getMaxNum(int[] nums, int k) {
        int[] ret = nums;
        for (int i = 1; i <= (nums.length - k); i++) {
            ret = getNextMaxNum(ret);
        }
        return ret;
    }

    // return max number possible with one less item
    private int[] getNextMaxNum(int[] nums) {
        int[] ret = new int[nums.length - 1];
        int i = 0, idxToDrop = nums.length - 1;
        while (i < nums.length - 1) {
            if (nums[i] >= nums[i + 1]) {
                i++;
            } else {//if (nums[i] < nums[i+1]) {
                idxToDrop = i;
                break;
            }
        }
        int k = 0;
        for (int l = 0; l < nums.length; l++) {
            if (l != idxToDrop) {
                ret[k++] = nums[l];
            }
        }
        return ret;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) return null;
        if (nums1 == null) return nums2;
        if (nums2 == null) return nums1;
        int[] ret = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                ret[k++] = nums2[j++];
            } else if (nums1[i] > nums2[j]) {
                ret[k++] = nums1[i++];
            } else { // they are equal
                int x = i, y = j;
                while (x < nums1.length && y < nums2.length && nums1[x] == nums2[y]) {
                    x++;
                    y++;
                }
                if (x == nums1.length && y == nums2.length) {
                    ret[k++] = nums1[i++];
                } else if (x < nums1.length && y == nums2.length) {
                    ret[k++] = nums1[i++];
                } else if (x == nums1.length && y < nums2.length) {
                    ret[k++] = nums2[j++];
                } else {
                    if (nums1[x] > nums2[y]) {
                        ret[k++] = nums1[i++];
                    } else {
                        ret[k++] = nums2[j++];
                    }
                }
            }
        }
        for (int x = i; x < nums1.length; x++) {
            ret[k++] = nums1[x];
        }
        for (int x = j; x < nums2.length; x++) {
            ret[k++] = nums2[x];
        }
        return ret;
    }

    // same length arrays
    private int[] getMax(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] > nums2[i]) {
                return nums1;
            } else if (nums1[i] == nums2[i]) {
                continue;
            } else {
                return nums2;
            }
        }
        return nums1;
    }

    public static void main(String[] args) {
        CreateMaximumNumber c = new CreateMaximumNumber();

        int[] n1 = {5, 0, 2, 1, 0, 1, 0, 3, 9, 1, 2, 8, 0, 9, 8, 1, 4, 7, 3};
        int[] n2 = {7, 6, 7, 1, 0, 1, 0, 5, 6, 0, 5, 0};
        int k = 31;
        Utils.printArray(c.maxNumber(n1, n2, k));

    }
}
