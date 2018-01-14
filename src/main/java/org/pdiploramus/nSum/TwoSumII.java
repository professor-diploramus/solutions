package org.pdiploramus.nSum;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
(Safely ignore this if you have done https://leetcode.com/problems/two-sum/description/

Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution and you may not use the same element twice.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
 */
public class TwoSumII {
  public int[] twoSum(int[] numbers, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i=0; i<numbers.length; i++) {
      int complement = target - numbers[i];
      if (map.containsKey(complement)) {
        return new int[]{map.get(complement), i+1};
      } else {
        map.put(numbers[i], i+1);
      }
    }
    return new int[]{};
  }

  @Test
  public void test() {
    Assert.assertArrayEquals(new int[]{1,2}, new TwoSumII().twoSum(new int[]{2, 7, 11, 1}, 9));
  }
}
