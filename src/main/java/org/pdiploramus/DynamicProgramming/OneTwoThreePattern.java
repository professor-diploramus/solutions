package org.pdiploramus.DynamicProgramming;

import org.junit.Assert;
import org.junit.Test;

/*
Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
Comments: for a number (k), I have to check if there's a number before it (j) that is greater than this number, and there must be a number before j (i) smaller than both j as well as k
 */
public class OneTwoThreePattern {
  public boolean find132pattern(int[] nums) {
    if (nums == null || nums.length < 3) return false;
    int[] leastNumBefore = new int[nums.length];
    leastNumBefore[0] = Integer.MAX_VALUE;
    for (int i = 1; i < leastNumBefore.length; i++) {
      leastNumBefore[i] = Math.min(leastNumBefore[i - 1], nums[i - 1]);
    }
    for (int i = 2; i < nums.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (nums[j] > nums[i] && leastNumBefore[j] < nums[i]) return true;
      }
    }
    return false;
  }

  @Test
  public void test() {
    Assert.assertTrue(new OneTwoThreePattern().find132pattern(new int[] {3, 1, 4, 2}));
    Assert.assertFalse(new OneTwoThreePattern().find132pattern(new int[] {1, 2, 3, 4}));
    Assert.assertFalse(new OneTwoThreePattern().find132pattern(new int[] {4, 3, 2, 1}));
    Assert.assertTrue(new OneTwoThreePattern().find132pattern(new int[] {-1, 3, 2, 0}));
    Assert.assertFalse(new OneTwoThreePattern().find132pattern(new int[] {-2, 1, -2}));
  }
}
