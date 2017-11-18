package org.pdiploramus;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/serialize-deserialize-binary-tree/
 */
public class SerializeBT {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        List<Integer> lst = new ArrayList<>();
        serializeHelper(root, lst);
        String ret = "";
        for (int n : lst) {
            ret += ":"+n;
        }
        return ret.substring(1);
    }

    private static void serializeHelper(TreeNode root, List<Integer> lst) {
        if (root == null) {
            lst.add(-1);
            return;
        }
        lst.add(root.val);
        serializeHelper(root.left, lst);
        serializeHelper(root.right, lst);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        Stack<Integer> stk = new Stack<>();
        String[] spl = data.split(":");
        for (int i=spl.length-1; i >=0; i--) {
            stk.push(Integer.parseInt(spl[i]));
        }
        return deserializeHelper(stk);
    }

    private static TreeNode deserializeHelper(Stack<Integer> stk) {
        int val = stk.pop();
        if (val == -1) return null;
        TreeNode tn = new TreeNode(val);
        tn.left = deserializeHelper(stk);
        tn.right = deserializeHelper(stk);
        return tn;
    }


    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
//        one.left = two;
//        one.right = three;

//        one.left = two;

//        one.left = two;
//        two.left = three;
//        two.right = four;
//        four.left = five;
//        four.right = six;

//        one.left = two;
//        two.left = three;
//        three.left = four;
//        four.left = five;

        one.right = two;
        two.right = three;
        three.right = four;
        four.right = five;

        Assert.assertEquals(serialize(one), serialize(deserialize(serialize(one))));

    }
}
