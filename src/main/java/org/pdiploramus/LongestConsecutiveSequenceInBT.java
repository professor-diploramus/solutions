package org.pdiploramus;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/longest-consecutive-sequence-binary-tree/
 */
public class LongestConsecutiveSequenceInBT {

    static class Node {
        int val;
        Node left, right;

        Node(int v) {
            this.val = v;
        }
    }

    static void findLongestConsecutiveSeq(Node root) {
        helper(root, null, "", 0);
    }

    static int longestSeqLength = 0;
    static String longestSeq;

    static void helper(Node root, Node parent, String seqSoFar, int lengthSoFar) {
        if (parent == null || root.val != parent.val + 1) {
            seqSoFar = ":" + root.val;
            lengthSoFar = 1;
        } else {
            seqSoFar = seqSoFar + ":" + root.val;
            ++lengthSoFar;
        }
        if (lengthSoFar > longestSeqLength) {
            longestSeqLength = lengthSoFar;
            longestSeq = seqSoFar;
        }
        if (root.left != null) {
            helper(root.left, root, seqSoFar, lengthSoFar);
        }
        if (root.right != null) {
            helper(root.right, root, seqSoFar, lengthSoFar);
        }
    }

    public static void main(String[] args) {

        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);
        Node ten = new Node(10);
        Node eleven = new Node(11);

//        six.right = nine;
//        nine.left = seven;
//        nine.right = ten;
//        ten.right = eleven;

        one.left = two;
        one.right = four;
        two.left = three;
        four.right = six;
        four.left = five;
        six.left = seven;

        findLongestConsecutiveSeq(one);
        System.out.println(longestSeqLength + " " + longestSeq);
    }
}
