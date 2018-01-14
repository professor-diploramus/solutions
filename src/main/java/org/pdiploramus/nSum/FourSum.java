package org.pdiploramus.nSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

Comments: Two pointers + twosum. Keep ignoring duplicates. Very similar to 3sum
 */
public class FourSum {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    List<List<Integer>> ret = new ArrayList<>();
    for (int i = 0; i <= nums.length - 4; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) continue;
      int j = i + 1;
      while (j <= nums.length - 3) {
        List<List<Integer>> l = twoSum(nums, nums[i], nums[j], j + 1, target - nums[i] - nums[j]);
        if (l.size() > 0) ret.addAll(l);
        int J = nums[j];
        while (j <= nums.length - 3 && nums[j] == J) ++j;
      }
    }
    return ret;
  }

  private List<List<Integer>> twoSum(int[] nums, int one, int two, int start, int target) {
    List<List<Integer>> ret = new ArrayList<>();
    int i = start, j = nums.length - 1;
    while (i < j) {
      int sum = nums[i] + nums[j];
      if (sum == target) {
        List<Integer> l = new ArrayList<>();
        l.add(one);
        l.add(two);
        l.add(nums[i]);
        l.add(nums[j]);
        ret.add(l);
        i = getNewIPos(nums, i, j);
        j = getNewJPos(nums, i, j);
      } else if (sum < target) {
        i = getNewIPos(nums, i, j);
      } else {
        j = getNewJPos(nums, i, j);
      }
    }
    return ret;
  }

  private int getNewIPos(int[] nums, int i, int j) {
    int curr = nums[i];
    while (i < j && nums[i] == curr) ++i;
    return i;
  }

  private int getNewJPos(int[] nums, int i, int j) {
    int curr = nums[j];
    while (i < j && nums[j] == curr) --j;
    return j;
  }

  public static void main(String[] args) {
    // System.out.println(new FourSum().fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0));
    System.out.println(new FourSum().fourSum(new int[] {1, 1, 1, 1}, 4));
  }
}
