package org.pdiploramus;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/*
https://leetcode.com/problems/subarray-sum-equals-k/description/

caterpillar method works only when each array element is > 0
the following method is more general.

Logic:
As you iterate the array, you calculate prefix sums.

If (prefix_sum - target) is found in the map => the subarray between
the indices where they were found and the current index will sum up
to k.

Record prefix_sum in a hashmap along with the index where the sum
was encountered for easy lookup.

seed the map with (0, [-1]) to handle the condition where prefix_sum == k
*/
public class SubarraySum {


  public int subarraySum(int[] nums, int k) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    map.put(0, new ArrayList<Integer>());
    map.get(0).add(-1);
    int prefixSum = 0, count = 0;
    for (int i=0; i<nums.length; i++) {
      prefixSum += nums[i];
      if (map.containsKey(prefixSum-k)) {
        List<Integer> l = map.get(prefixSum-k);
        count += l.size();
        // print the subarrays
        for (int x:l){
          System.out.println(x+1+","+i);
        }
      }
      if (map.containsKey(prefixSum)) {
        map.get(prefixSum).add(i);
      } else {
        map.put(prefixSum, new ArrayList<Integer>());
        map.get(prefixSum).add(i);
      }
    }
    return count;
  }

  @Test
  public void tests() {
    assertEquals(3,new SubarraySum().subarraySum(new int[] {10, 2, -2, -20, 10}, -10));
    assertEquals(1, new SubarraySum().subarraySum(new int[] {1, 4, 20, 3, 10, 5}, 33));
    assertEquals(3, new SubarraySum().subarraySum(new int[] {10, 2, -2, -20, 10}, -10));
    assertEquals(0, new SubarraySum().subarraySum(new int[] {-10, 0, 2, -2, -20, 10}, 20));
    assertEquals(2, new SubarraySum().subarraySum(new int[] {1, 1, 1}, 2));
  }
}
