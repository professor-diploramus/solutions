package org.pdiploramus.nSum;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
https://leetcode.com/problems/two-sum-iii-data-structure-design/
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,

add(1);
add(3);
add(5);
find(4) -> true
find(7) -> false


Comment: Make find O(1)
 */
public class TwoSumIII {

  Set<Integer> set = new HashSet<>();
  List<Integer> allNumbers = new ArrayList<>();

  public void add(int n) {
    for (int i : allNumbers) {
      set.add(i + n);
    }
    allNumbers.add(n);
  }

  public boolean find(int n) {
    return set.contains(n);
  }

  @Test
  public void test() {
    TwoSumIII t = new TwoSumIII();
    t.add(1);
    t.add(3);
    t.add(5);
    Assert.assertTrue(t.find(4));
    Assert.assertFalse(t.find(7));
  }
}
