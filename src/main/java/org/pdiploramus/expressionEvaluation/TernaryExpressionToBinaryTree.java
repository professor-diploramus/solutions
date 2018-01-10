package org.pdiploramus.expressionEvaluation;

import java.util.Stack;

// https://www.geeksforgeeks.org/convert-ternary-expression-binary-tree/
public class TernaryExpressionToBinaryTree {
  private static class Node {
    char c;
    Node left, right;
    Node(char c) {
      this.c = c;
    }
  }

  public Node convert(String s) {
    Stack<Node> stk = new Stack<>();
    for (int i=s.length()-1; i>=0; i--) {
      if (stk.isEmpty() || stk.peek().c != '?') {
        stk.push(new Node(s.charAt(i)));
      } else {
        stk.pop(); // ?
        Node c1 = stk.pop();
        stk.pop(); // :
        Node c2 = stk.pop();
        Node c3 = new Node(s.charAt(i));
        c3.right = c2;
        c3.left = c1;
        stk.push(c3);
      }
    }
    return stk.pop();
  }

  public static void main(String[] args) {
    Node n = new TernaryExpressionToBinaryTree().convert("a?b?c:d:e");
    System.out.println();
  }
}
