package org.pdiploramus;

import org.junit.Assert;

/**
 * http://www.geeksforgeeks.org/painting-fence-algorithm/
 * I think the algo described in the above url is not right.
 * same[i]: number of ways I can paint fence i with the same color as the last one
 * diff[i]: number of ways I can paint fence i with a different color from the last one
 * same[i] = diff[i-1]. The only way I can color fence i same as i-1 is if i-2 is colored differently
 * diff[i] = total[i] * (k-1)
 * setting the values for i=0 is tricky
 */
public class PaintingFence {

    static long countWays(int n, int k) {
        int[] same = new int[n];
        int[] diff = new int[n];
        int[] total = new int[n];

        same[0] = 0;
        diff[0] = k;
        total[0] = same[0]+diff[0];

        for (int i=1; i<n; i++) {
            same[i] = diff[i-1];
            diff[i] = total[i-1]*(k-1);
            total[i] = same[i] + diff[i];
        }
        return total[n-1];
    }

    /*
    This is the leetcode solution
     */
    static int numWays(int n, int k) {
        if(n == 0) return 0;
        else if(n == 1) return k;
        int diffColorCounts = k*(k-1);
        int sameColorCounts = k;
        for(int i=2; i<n; i++) {
            int temp = diffColorCounts;
            diffColorCounts = (diffColorCounts + sameColorCounts) * (k-1);
            sameColorCounts = temp;
        }
        return diffColorCounts + sameColorCounts;
    }

    public static void main(String[] args) {
        for (int i=2; i<=5; i++) {
            for (int j=2; j<=5; j++) {
                System.out.println(i + "," + j + " " + numWays(i,j) + " " + countWays(i,j));
                Assert.assertEquals(numWays(i,j), countWays(i,j));
            }
        }
    }

}
