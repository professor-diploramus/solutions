package org.pdiploramus;

public class WiggleSort {

    public void wiggleSortI(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 != 0) {
                if (nums[i - 1] > nums[i]) {
                    swap(i-1, i, nums);
                }
                if (i < nums.length-1) {
                    if (nums[i + 1] > nums[i]) {
                        swap(i + 1, i, nums);
                    }
                }
            }
        }
    }


    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {5,4,3,2,1,0};
        new WiggleSort().wiggleSortI(nums);
        for (int i : nums) System.out.print(i + ",");

    }
}
