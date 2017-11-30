package org.pdiploramus;

public class IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        boolean[] h = new boolean[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (h[j]) return true;
                    else h[i] = true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 8, 1, 4, 2, 1};
        System.out.println(new IncreasingTripletSubsequence().increasingTriplet(nums));
    }
}
