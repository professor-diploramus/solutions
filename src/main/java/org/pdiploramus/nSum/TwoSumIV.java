package org.pdiploramus.nSum;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/

Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False

 */
public class TwoSumIV {

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public boolean findTarget(TreeNode root, int k) {
    Set<Integer> set = new HashSet<>();
    return preorder(root, set, k);
  }

  private boolean preorder(TreeNode root, Set<Integer> set, int target) {
    if (root == null) return false;
    int complement = target - root.val;
    if (set.contains(complement)) return true;
    set.add(root.val);
    boolean b = preorder(root.left, set, target);
    if (b) return b;
    else return preorder(root.right, set, target);
  }
}
