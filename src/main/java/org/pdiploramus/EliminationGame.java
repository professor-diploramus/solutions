package org.pdiploramus;

import java.util.Stack;

public class EliminationGame {
  public int lastRemaining(int n) {
      Stack<Integer> stk1 = new Stack<>();
      Stack<Integer> stk2 = new Stack<>();
      for (int i=n; i>=1; i--) {
          stk1.push(i);
      }
      while (true) {
          int cnt = 1;
          while (!stk1.isEmpty()) {
              int i = stk1.pop();
              if (cnt % 2 == 0) stk2.push(i);
              cnt++;
          }
          if (stk2.size() == 1) return stk2.pop();
          Stack<Integer> temp = stk1;
          stk1 = stk2;
          stk2 = temp;
      }
  }

  public static void main(String[] args) {
    System.out.println(new EliminationGame().lastRemaining(9));
  }
}
