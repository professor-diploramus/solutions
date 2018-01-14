package org.pdiploramus.nSum;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/two-sum/description/

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].


Solution Approaches:
A key goal is that the same element should not be counted twice

Approach 1: sort the array and initialize two pointers - start and end.
increase start or decrease end depending on whether the sum is < or > target.
If the question asks to return the index pairs of the original array,
this approach becomes slightly hard, since you need to create an array
of indices and sort that one using the array elements in the comparator.
Otherwise, this approach works best when you are asked to return the
array elements rather than the indices.

Approach 2: This approach uses a hash map to make locating the complement
easy.
In a general sense, create a map with the array elements as keys
and a list of locations where the element occurs as value. Now iterate the
array and return indices that are not the same as the current index.
Simplification - Build the map as you iterate the array. This obviates the
"same index check".

The implementation below is slightly specific since the question specifies
that there'll be exactly one solution.
*/

public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if (map.containsKey(complement)) {
        return new int[] {map.get(complement), i};
      } else {
        map.put(nums[i], i);
      }
    }
    return new int[] {};
  }

  @Test
  public void test() {
    Assert.assertArrayEquals(new int[] {0, 1}, new TwoSum().twoSum(new int[] {2, 7, 11, 15}, 9));
  }
}
