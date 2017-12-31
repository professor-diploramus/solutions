package org.pdiploramus;

public class NumberOfArithmeticSlices {
  public int numberOfArithmeticSlices(int[] A) {
    int total = 0;
    if (A == null || A.length < 3) return 0;
    int i = 0, j = 2;
    while (j < A.length) {
      while (j < A.length && A[j] - A[j - 1] == A[j - 1] - A[j - 2]) {
        j++;
      }
      if (j - 1 - i >= 2) {
        // calculate total slices here
        System.out.println(A[i] + "," + A[j - 1]);
        int n = j - i;
        for (int k = 3; k <= n; k++) {
          total += (n - k + 1);
        }
      }
      i = j - 1;
      j++;
    }
    return total;
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4};
    new NumberOfArithmeticSlices().numberOfArithmeticSlices(nums);
  }
}
