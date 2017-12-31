package org.pdiploramus;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/split-array-largest-sum/description/
public class SplitArrayLargestSum {
  static class S {
    int[] arr;
    List<Integer> splits = new ArrayList<>();
    S(int[] a) {
      this.arr = a;
    }
  }
  public int splitArray(int[] nums, int m) {
    if (m == 1) {
      int sum = 0;
      for (int j = 0; j<nums.length; j++) {
        sum += nums[j];
      }
      return sum;
    }
    int ret = Integer.MAX_VALUE;
    Stack<S> stk = new Stack<>();
    for (int i=0; i <= nums.length-2; i++) {
      S s = new S(nums);
      s.splits.add(i);
      stk.push(s);
    }
    while (!stk.isEmpty()) {
      S spop = stk.pop();
      if (spop.splits.size() == m-1) {
        int maxSum = Integer.MIN_VALUE;
        int prev = 0;
        for (int i : spop.splits) {
          int sum = 0;
          for (int j = prev; j<=i; j++) {
            sum += nums[j];
          }
          maxSum = Math.max(maxSum, sum);
          prev = i+1;
        }
        int sum = 0;
        for (int j = prev; j<nums.length; j++) {
          sum += nums[j];
        }
        maxSum = Math.max(maxSum, sum);
        ret = Math.min(ret, maxSum);
      } else {
        int last = spop.splits.get(spop.splits.size()-1);
        for (int i=last+1; i <= nums.length-2; i++) {
          S s = new S(nums);
          s.splits.addAll(spop.splits);
          s.splits.add(i);
          stk.push(s);
        }
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    System.out.println(new SplitArrayLargestSum().splitArray(new int[]{7,2,5,10,8}, 2));
  }
}
