package org.pdiploramus;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<>();
        Stack<Integer> stk = new Stack<>();
        for (int i = 9; i >= 1; i--) {
            if (i <= n)
                stk.push(i);
        }
        while (!stk.isEmpty()) {
            Integer spop = stk.pop();
            ret.add(spop);
            String s = spop.toString();
            for (int i = 9; i >= 0; i--) {
                int ns = Integer.parseInt(s + i);
                if (ns <= n) {
                    stk.push(ns);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new LexicographicalNumbers().lexicalOrder(1050));
    }
}
