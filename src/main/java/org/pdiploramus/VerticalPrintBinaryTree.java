package org.pdiploramus;

import java.util.*;

/**
 *
 */
public class VerticalPrintBinaryTree {
    static class Node {
        int val;
        Node left, right;

        Node(int v) {
            this.val = v;
        }
    }

    public static List<List<Integer>> verticalOrder(Node root) {
        List<List<Integer>> ret = new ArrayList<>();
        Map<Integer, List<Integer>> mp = new TreeMap<>();
        preOrder(root, 0, 0, mp);
        for (int i : mp.keySet()) {
            ret.add(mp.get(i));
        }
        return ret;
    }

    private static void preOrder(Node n, int x, int y, Map<Integer, List<Integer>> coods) {
        if (n == null) return;
        if (!coods.containsKey(x)) {
            coods.put(x, new ArrayList<>());
        }
        coods.get(x).add(n.val);
        preOrder(n.left, x-1, y+1, coods);
        preOrder(n.right, x+1, y+1, coods);
    }

    public static void main(String[] args) {
//        Node three = new Node(3);
//        Node nine = new Node(9);
//        Node twenty = new Node(20);
//        Node four = new Node(4);
//        Node five = new Node(5);
//        Node two = new Node(2);
//        Node seven = new Node(7);
//        three.left = nine;
//        three.right = twenty;
//        nine.left = four;
//        nine.right = five;
//        twenty.left = two;
//        twenty.right = seven;

        Node three = new Node(3);
        Node nine = new Node(9);
        Node twenty = new Node(20);
        Node fifteen = new Node(15);
        Node seven = new Node(7);
        three.left = nine;
        three.right = twenty;
        twenty.left = fifteen;
        twenty.right = seven;

        System.out.println(verticalOrder(three));



    }
}
