package org.pdiploramus;

// https://leetcode.com/problems/third-maximum-number/description/
public class ThirdLargest {

  public int thirdMax(int[] nums) {
    Integer one = null, two = null, three = null;
    for (int i = 0; i < nums.length; i++) {
      int n = nums[i];
      if ((one != null && n == one) || (two != null && n == two) || (three != null && n == three))
        continue;
      if (one == null || n > one) {
        three = two;
        two = one;
        one = n;
      } else if (two == null || n > two) {
        three = two;
        two = n;
      } else if (three == null || n > three) {
        three = n;
      }
    }
    return (three == null ? one : three);
  }

  public static void main(String[] args) {
    int[] arr = {1, 2};
    System.out.println(new ThirdLargest().thirdMax(arr));
  }
}
