package org.pdiploramus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SubsetSumGraphApproach {
  List<Integer> getSubsetWithTargetSum(int[] arr, int target) {
    List<Integer> ret = new ArrayList<>();
    class Node {
      int[] arr;
      boolean[] pos;
      int sum;

      Node(int[] a, boolean[] p, int sum) {
        this.arr = a;
        this.pos = p;
        this.sum = sum;
      }
    }
    Stack<Node> stk = new Stack<>();
    stk.push(new Node(arr, new boolean[arr.length], Arrays.stream(arr).sum()));
    while (!stk.isEmpty()) {
      Node n = stk.pop();
      if (n.sum == target) {
        for (int i = 0; i < n.pos.length; i++) {
          if (!n.pos[i]) {
            ret.add(arr[i]);
          }
        }
        break;
      } else if (n.sum > target) {
        for (int i = 0; i < n.pos.length; i++) {
          boolean[] pclone = n.pos.clone();
          if (!pclone[i]) {
            pclone[i] = true;
            stk.push(new Node(arr, pclone, n.sum-n.arr[i]));
          }
        }
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    int[] arr = {3, 34, 4, 12, 5, 2};
    System.out.println(new SubsetSumGraphApproach().getSubsetWithTargetSum(arr, 25));
  }
}
