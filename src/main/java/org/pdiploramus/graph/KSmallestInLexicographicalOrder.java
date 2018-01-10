package org.pdiploramus.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;
import java.util.stream.IntStream;

/*
Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

Note: 1 ≤ k ≤ n ≤ 109.

Example:

Input:
n: 13   k: 2

Output:
10

Explanation:
The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 */
public class KSmallestInLexicographicalOrder {

  // This solution times out on leetcode.
  // See https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/discuss/92242
  public int findKthNumber(int n, int k) {
    Stack<Integer> stk = new Stack<>();
    int counter = 0;
    IntStream.iterate(9, x-> x-1).limit(9).forEach(stk::push);
    while (!stk.isEmpty()) {
      Integer pop = stk.pop();
      if (pop > n) continue;
      ++counter;
      if (counter == k) return pop;
      IntStream.iterate(9, x-> x-1).limit(10).forEach(x -> {
        int nw = Integer.valueOf(String.valueOf(pop) + x);
        if (nw <= n) stk.push(nw);
      });
    }
    return -1;
  }

  @Test
  public void test() {
    Assert.assertEquals(1, new KSmallestInLexicographicalOrder().findKthNumber(13, 1));
    Assert.assertEquals(10, new KSmallestInLexicographicalOrder().findKthNumber(13, 2));
    Assert.assertEquals(11, new KSmallestInLexicographicalOrder().findKthNumber(13, 3));
    Assert.assertEquals(12, new KSmallestInLexicographicalOrder().findKthNumber(13, 4));
    Assert.assertEquals(13, new KSmallestInLexicographicalOrder().findKthNumber(13, 5));
    Assert.assertEquals(2, new KSmallestInLexicographicalOrder().findKthNumber(13, 6));
    Assert.assertEquals(3, new KSmallestInLexicographicalOrder().findKthNumber(13, 7));
    Assert.assertEquals(4, new KSmallestInLexicographicalOrder().findKthNumber(13, 8));
    Assert.assertEquals(5, new KSmallestInLexicographicalOrder().findKthNumber(13, 9));
    Assert.assertEquals(6, new KSmallestInLexicographicalOrder().findKthNumber(13, 10));
    Assert.assertEquals(7, new KSmallestInLexicographicalOrder().findKthNumber(13, 11));
    Assert.assertEquals(8, new KSmallestInLexicographicalOrder().findKthNumber(13, 12));
    Assert.assertEquals(9, new KSmallestInLexicographicalOrder().findKthNumber(13, 13));
  }
}
