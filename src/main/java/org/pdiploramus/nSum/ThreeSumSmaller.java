package org.pdiploramus.nSum;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/*
https://leetcode.com/problems/3sum-smaller/

Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]

Comment: see https://www.geeksforgeeks.org/count-triplets-with-sum-smaller-that-a-given-value/

 */
public class ThreeSumSmaller {

  public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);
    int ret = 0;
    for (int i = 0; i <= nums.length - 3; i++) {
      int x = target - nums[i];
      ret += helper(nums, i + 1, x);
    }
    return ret;
  }

  private int helper(int[] nums, int start, int target) {
    int counter = 0;
    int i = start, j = nums.length - 1;
    while (i < j) {
      if (nums[i] + nums[j] < target) {
           counter += (j-i);
           ++i;
      } else {
        --j;
      }
    }
    return counter;
  }

  @Test
  public void test() {
    Assert.assertEquals(2, new ThreeSumSmaller().threeSumSmaller(new int[] {-2, 0, 1, 3}, 2));
    Assert.assertEquals(4, new ThreeSumSmaller().threeSumSmaller(new int[] {5, 1, 3, 4, 7}, 12));
    Assert.assertEquals(
        7, new ThreeSumSmaller().threeSumSmaller(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 10));
    Assert.assertEquals(
        6, new ThreeSumSmaller().threeSumSmaller(new int[] {3, 7, 9, 1, 2, 5, 11, 4}, 10));
  }
}
