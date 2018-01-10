package org.pdiploramus.intervals;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/*
There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 */
public class MinArrowsToBurstBalloons {

  // Use the first test case to understand why you have to sort the
  // points by their ends and not their starts.
  public int findMinArrowShots(int[][] points) {
    if (points == null || points.length == 0) return 0;
    Arrays.sort(
        points,
        new Comparator<int[]>() {
          @Override
          public int compare(int[] o1, int[] o2) {
            return Integer.compare(o1[1], o2[1]);
          }
        });
    Stack<int[]> stk = new Stack<>();
    stk.push(points[0]);
    for (int i = 1; i < points.length; i++) {
      if (points[i][0] > stk.peek()[1]) {
        stk.push(points[i]);
      }
    }
    return stk.size();
  }

  @Test
  public void test() {
    Assert.assertEquals(
        2,
        new MinArrowsToBurstBalloons()
            .findMinArrowShots(new int[][] {{0,5}, {3,4}, {1, 2}}));
    Assert.assertEquals(
        2,
        new MinArrowsToBurstBalloons()
            .findMinArrowShots(new int[][] {{10, 16}, {2, 8}, {1, 6}, {7, 12}}));
    Assert.assertEquals(
        2,
        new MinArrowsToBurstBalloons()
            .findMinArrowShots(
                new int[][] {
                  {3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}
                }));
  }
}
