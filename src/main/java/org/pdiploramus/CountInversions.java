package org.pdiploramus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class CountInversions {

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        res.add(0);
        for (int i = nums.length - 2; i >= 0; i--) {
            int count = insertNode(root, nums[i]);
            res.add(count);
        }
        Collections.reverse(res);
        return res;
    }

    public int insertNode(TreeNode root, int val) {
        int thisCount = 0;
        while (true) {
            if (val <= root.val) {
                root.count++;
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                thisCount += root.count;
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return thisCount;
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int count = 1;

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", count=" + count +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,2,2,6,1};
        System.out.println(new CountInversions().countSmaller(nums));
    }
}
