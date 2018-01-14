package org.pdiploramus.nSum;

import java.util.*;

/*
https://leetcode.com/problems/3sum/description/

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

Solution Approach: See the approach lined out in the TwoSum solution
The tricky part in this problem is the requirement that the result
tuples should be unique. I have implemented that requirement
without using sets or similar data structures.

*/
public class ThreeSum {
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ret = new ArrayList<>();
    for (int i = 0; i <= nums.length - 3; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) continue;
      int target = 0 - nums[i];
      List<List<Integer>> l = twoSum(nums, target, i + 1, nums[i]);
      if (l.size() > 0) {
        ret.addAll(l);
      }
    }
    return ret;
  }

  private List<List<Integer>> twoSum(int[] nums, int target, int start, int firstVal) {
    List<List<Integer>> ret = new ArrayList<>();
    int i = start, j = nums.length - 1;
    while (i < j) {
      int curri = nums[i];
      int currj = nums[j];
      int sum = curri + currj;
      if (sum == target) {
        List<Integer> l = new ArrayList<>();
        l.add(firstVal);
        l.add(nums[i]);
        l.add(nums[j]);
        ret.add(l);
        while(j >= i && currj == nums[j]) --j;
        while(j >= i && curri == nums[i]) ++i;
      } else if (sum > target) {
        while(j >= i && currj == nums[j]) --j;
      } else {
        while(j >= i && curri == nums[i]) ++i;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    System.out.println(new ThreeSum().threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
  }
}
