package org.pdiploramus;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class WaterJug {

    private void helper(Stack<String> stk, Set<String> visited, String s) {
        if (!visited.contains(s)) {
            visited.add(s);
            stk.push(s);
        }
    }

    public boolean canMeasure(int x, int y, int z) {
        Stack<String> stk = new Stack<>();
        Set<String> visited = new HashSet<>();
        stk.push("0,0");
        visited.add("0,0");
        while (!stk.isEmpty()) {
            String pop = stk.pop();
            String[] spl = pop.split(",");
            System.out.println(pop);
            int jugx = Integer.valueOf(spl[0]);
            int jugy = Integer.valueOf(spl[1]);
            if (jugx == z || jugy == z) {
                return true;
            }
            // Fill any of the jugs completely with water.
            if (jugx < x) {
                helper(stk, visited, x + "," + jugy);
            }
            if (jugy < y) {
                helper(stk, visited, jugx + "," + y);
            }
            // Empty any of the jugs.
            if (jugx > 0) {
                helper(stk, visited, 0 + "," + jugy);
            }
            if (jugy > 0) {
                helper(stk, visited, jugx + "," + 0);
            }
            // Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
            int jugxrem = Math.max(0, x - jugx);
            int jugyrem = Math.max(0, y - jugy);
            // x<-y
            if (jugxrem >= jugy) {
                helper(stk, visited, (jugx + jugy) + "," + 0);
            } else {
                helper(stk, visited, x + "," + Math.max(0, jugy - jugxrem));
            }
            // y<-x
            if (jugyrem >= jugx) {
                helper(stk, visited, "0" + "," + (jugx + jugy));
            } else {
                helper(stk, visited, Math.max(0, jugx - jugyrem) + "," + y);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new WaterJug().canMeasure(2, 6, 5));
    }
}
