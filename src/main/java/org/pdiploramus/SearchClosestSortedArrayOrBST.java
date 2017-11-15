package org.pdiploramus;

import org.junit.Assert;

/**
 * Search for value closest to target in Array or BST
 */
public class SearchClosestSortedArrayOrBST {
    static class Node {
        int n;
        Node left, right;

        Node(int n) {
            this.n = n;
        }
    }

    static int searchBST(Node n, int target) {
        if (n.n == target) return n.n;
        if (target < n.n) {
            if (n.left == null) {
                return n.n;
            } else if (target < n.left.n) {
                return searchBST(n.left, target);
            } else {
                if (n.n - target < target - n.left.n) {
                    return n.n;
                } else {
                    return n.left.n;
                }
            }
        } else {
            if (n.right == null) {
                return n.n;
            } else if (target > n.right.n) {
                return searchBST(n.right, target);
            } else {
                if (n.right.n - target < target - n.n) {
                    return n.right.n;
                } else {
                    return n.n;
                }

            }
        }

    }


    static int searchArray(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (true) {
            int mid = (i + j) / 2;
            if (arr[mid] == target) {
                return arr[mid];
            }
            if (arr[mid] > target) {
                if (mid == 0) {
                    return arr[0];
                }
                if (target < arr[mid - 1]) {
                    j = mid - 1;
                    continue;
                } else {
                    if (target - arr[mid - 1] > arr[mid] - target) {
                        return arr[mid];
                    } else {
                        return arr[mid - 1];
                    }
                }
            } else {
                if (mid == arr.length - 1) {
                    return arr[arr.length - 1];
                }
                if (target > arr[mid + 1]) {
                    i = mid + 1;
                    continue;
                } else {
                    if (arr[mid + 1] - target > target - arr[mid]) {
                        return arr[mid];
                    } else {
                        return arr[mid + 1];
                    }
                }
            }
        }

    }


    public static void main(String[] args) {
        int[] arr = {3, 6, 9, 12};
        Assert.assertEquals(3, searchArray(arr, 0));
        Assert.assertEquals(3, searchArray(arr, 3));
        Assert.assertEquals(3, searchArray(arr, 4));
        Assert.assertEquals(6, searchArray(arr, 5));
        Assert.assertEquals(6, searchArray(arr, 6));
        Assert.assertEquals(6, searchArray(arr, 7));
        Assert.assertEquals(9, searchArray(arr, 8));
        Assert.assertEquals(9, searchArray(arr, 9));
        Assert.assertEquals(9, searchArray(arr, 10));
        Assert.assertEquals(12, searchArray(arr, 11));
        Assert.assertEquals(12, searchArray(arr, 12));
        Assert.assertEquals(12, searchArray(arr, 13));

        Node three = new Node(3);
        Node six = new Node(6);
        Node nine = new Node(9);
        Node twelve = new Node(12);

        six.left = three;
        six.right = nine;
        nine.right = twelve;
        Assert.assertEquals(3, searchBST(six, 0));
        Assert.assertEquals(3, searchBST(six, 3));
        Assert.assertEquals(3, searchBST(six, 4));
        Assert.assertEquals(6, searchBST(six, 5));
        Assert.assertEquals(6, searchBST(six, 6));
        Assert.assertEquals(6, searchBST(six, 7));
        Assert.assertEquals(9, searchBST(six, 8));
        Assert.assertEquals(9, searchBST(six, 9));
        Assert.assertEquals(9, searchBST(six, 10));
        Assert.assertEquals(12, searchBST(six, 11));
        Assert.assertEquals(12, searchBST(six, 12));
        Assert.assertEquals(12, searchBST(six, 13));


        three.right = six;
        six.right = nine;
        nine.right = twelve;
        Assert.assertEquals(3, searchBST(three, 0));
        Assert.assertEquals(3, searchBST(three, 3));
        Assert.assertEquals(3, searchBST(three, 4));
        Assert.assertEquals(6, searchBST(three, 5));
        Assert.assertEquals(6, searchBST(three, 6));
        Assert.assertEquals(6, searchBST(three, 7));
        Assert.assertEquals(9, searchBST(three, 8));
        Assert.assertEquals(9, searchBST(three, 9));
        Assert.assertEquals(9, searchBST(three, 10));
        Assert.assertEquals(12, searchBST(three, 11));
        Assert.assertEquals(12, searchBST(three, 12));
        Assert.assertEquals(12, searchBST(three, 13));

    }
}
