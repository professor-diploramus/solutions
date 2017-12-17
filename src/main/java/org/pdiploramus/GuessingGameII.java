package org.pdiploramus;

import java.util.Stack;

public class GuessingGameII {


    // did not fully understand the problem
    public int getMoneyAmount(int n) {
        if (n == 1) return 0;
        int maxMoney = 0;
        class Node {
            int l, u, mid, money;

            Node(int l, int u, int m) {
                this.l = l;
                this.u = u;
                this.mid = (l + u) / 2;
                this.money = m + mid;
            }
        }
        Stack<Node> stk = new Stack<>();
        Node newnode = new Node(1, n, 0);
        stk.push(newnode);
        while (!stk.isEmpty()) {
            Node node = stk.pop();
            maxMoney = Math.max(maxMoney, node.money);
            if (node.mid - 1 > node.l) {
                newnode = new Node(node.l, node.mid - 1, node.money);
                stk.push(newnode);
            }
            if (node.mid + 1 < node.u) {
                newnode = new Node(node.mid + 1, node.u, node.money);
                stk.push(newnode);
            }
        }
        return maxMoney;
    }

    public static void main(String[] args) {
        System.out.println(new GuessingGameII().getMoneyAmount(10));
    }
}
