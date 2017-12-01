package org.pdiploramus;

public class Utils {
    public static void printArray(int[] arr) {
        for (int i : arr) System.out.print(i + ",");
        System.out.println();
    }

    static class TreeNode {
        int s;
        TreeNode left, right;
        TreeNode(int s) {
            this.s = s;
        }
        @Override
        public String toString() {
            return s + "";
        }
    }

}
