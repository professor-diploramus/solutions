package org.pdiploramus.codingPractice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/find-all-duplicates-in-an-array/description/

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
 */
public class FindAllDuplicatesInAnArray {
  public List<Integer> findDuplicates(int[] nums) {
    List<Integer> ret = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int v = Math.abs(nums[i]);
      int belongsToIdx = v - 1;
      if (nums[belongsToIdx] < 0) {
        ret.add(v);
      } else {
        nums[belongsToIdx] = -1 * nums[belongsToIdx];
      }
    }
    return ret;
  }

  @Test
  public void test() {
    org.junit.Assert.assertEquals(Arrays.asList(1), new FindAllDuplicatesInAnArray().findDuplicates(new int[] {1, 1}));
    org.junit.Assert.assertEquals(Arrays.asList(2), new FindAllDuplicatesInAnArray().findDuplicates(new int[] {2, 2}));
    org.junit.Assert.assertEquals(
        Arrays.asList(2, 3),
        new FindAllDuplicatesInAnArray()
            .findDuplicates(new int[] {4, 3, 2, 7, 8, 2, 3, 1})
            .stream()
            .sorted()
            .collect(Collectors.toList()));
  }
}
