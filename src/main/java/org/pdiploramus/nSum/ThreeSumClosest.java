package org.pdiploramus.nSum;

import org.junit.Assert;
import org.junit.Test;
import org.pdiploramus.SummaryRanges;

import java.util.Arrays;

/*

Given an array S of n integers, find three integers in S such that the sum
is closest to a given number, target. Return the sum of the three integers.
You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Comments: The main thing to note when calculating two-sum-closest in
a sorted array using the two-pointer method:
1 6 14 17 23 39, target 20
Initially, the diff will decrease with --j
when i=1, j=17, you start increasing i, and the diff starts increasing.
This is not an exit condition


*/
public class ThreeSumClosest {

  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int smallesDiff = Integer.MAX_VALUE;
    int closest = 0;
    for (int i = 0; i <= nums.length - 3; i++) {
      int closestCurr = twoSumClosest(nums, i + 1, target - nums[i]) + nums[i];
      if (Math.abs(target - closestCurr) < smallesDiff) {
        smallesDiff = Math.abs(target - closestCurr);
        closest = closestCurr;
      }
    }
    return closest;
  }

  private int twoSumClosest(int[] nums, int start, int target) {
    int minDiff = Integer.MAX_VALUE;
    int closest = 0;
    int i= start, j=nums.length-1;
    while (i < j) {
      int sum = nums[i] + nums[j];
      int diff = Math.abs(sum - target);
      if (diff < minDiff) {
        minDiff = diff;
        closest = sum;
      }
      if (sum == target) return closest;
      else if (sum < target) {
        ++i;
      } else {
        --j;
      }
    }
    return closest;
  }

  @Test
  public void test() {
    Assert.assertEquals(2, new ThreeSumClosest().threeSumClosest(new int[] {-1, 2, 1, -4}, 1));
    Assert.assertEquals(13, new ThreeSumClosest().threeSumClosest(new int[] {1, 2, 5, 10, 11}, 12));
    Assert.assertEquals(
        0,
        new ThreeSumClosest()
            .threeSumClosest(new int[] {-55, -24, -18, -11, -7, -3, 4, 5, 6, 9, 11, 23, 33}, 0));
  }
}
