package org.pdiploramus;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class AndroidUnlockPattern {

    private int dfs(Set<String> visited, Stack<String> stk, int m, int n) {
        int total = 0;
        while (!stk.isEmpty()) {
            String pop = stk.pop();
            String[] popSplit = pop.split(":");
            if (popSplit.length >= m && popSplit.length <= n) {
                System.out.println(pop);
                total++;
                if (popSplit.length == n) {
                    continue;
                }
            }
            String[] lastPos = popSplit[popSplit.length - 1].split(",");
            int r = Integer.parseInt(lastPos[0]);
            int c = Integer.parseInt(lastPos[1]);

            // r-1, c-1
            if (!visited.contains((r - 1) + "," + (c - 1))) {
                pushIfValid(r - 1, c - 1, stk, pop, visited);
            } else {
                pushIfValid((r - 2), (c - 2), stk, pop, visited);
            }
            // r-1, c
            if (!visited.contains((r - 1) + "," + (c))) {
                pushIfValid(r - 1, c, stk, pop, visited);
            } else {
                pushIfValid((r - 2), (c), stk, pop, visited);
            }
            // r-1, c+1
            if (!visited.contains((r - 1) + "," + (c + 1))) {
                pushIfValid(r - 1, c + 1, stk, pop, visited);
            } else {
                pushIfValid((r - 2), (c + 2), stk, pop, visited);
            }
            // r, c-1
            if (!visited.contains((r) + "," + (c - 1))) {
                pushIfValid(r, c - 1, stk, pop, visited);
            } else {
                pushIfValid((r), (c - 2), stk, pop, visited);
            }
            // r, c+1
            if (!visited.contains((r) + "," + (c + 1))) {
                pushIfValid(r, c + 1, stk, pop, visited);
            } else {
                pushIfValid((r), (c + 2), stk, pop, visited);
            }
            // r+1, c-1
            if (!visited.contains((r + 1) + "," + (c - 1))) {
                pushIfValid(r + 1, c - 1, stk, pop, visited);
            } else {
                pushIfValid((r + 2), (c - 2), stk, pop, visited);
            }

            // r+1, c
            if (!visited.contains((r + 1) + "," + (c))) {
                pushIfValid(r + 1, c, stk, pop, visited);
            } else {
                pushIfValid((r + 2), (c), stk, pop, visited);
            }

            // r+1, c+1
            if (!visited.contains((r + 1) + "," + (c + 1))) {
                pushIfValid(r + 1, c + 1, stk, pop, visited);
            } else {
                pushIfValid((r + 2), (c + 2), stk, pop, visited);
            }

            // r+2, c+1
            if (!visited.contains((r + 2) + "," + (c + 1))) {
                pushIfValid(r + 2, c + 1, stk, pop, visited);
            }

            // r+2, c-1
            if (!visited.contains((r + 2) + "," + (c - 1))) {
                pushIfValid(r + 2, c - 1, stk, pop, visited);
            }

            // r-2, c+1
            if (!visited.contains((r - 2) + "," + (c + 1))) {
                pushIfValid(r - 2, c + 1, stk, pop, visited);
            }

            // r-2, c-1
            if (!visited.contains((r - 2) + "," + (c - 1))) {
                pushIfValid(r - 2, c - 1, stk, pop, visited);
            }


            // r+1, c+2
            if (!visited.contains((r + 1) + "," + (c + 2))) {
                pushIfValid(r + 1, c + 2, stk, pop, visited);
            }

            // r-1, c+2
            if (!visited.contains((r - 1) + "," + (c + 2))) {
                pushIfValid(r - 1, c + 2, stk, pop, visited);
            }

            // r+1, c-2
            if (!visited.contains((r + 1) + "," + (c - 2))) {
                pushIfValid(r + 1, c - 2, stk, pop, visited);
            }

            // r-1, c-2
            if (!visited.contains((r - 1) + "," + (c - 2))) {
                pushIfValid(r - 1, c - 2, stk, pop, visited);
            }
        }
        return total;
    }

    public int numberOfPatterns(int m, int n) {
        int total = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                Set<String> visited = new HashSet<>();
                Stack<String> stk = new Stack<>();
                stk.push(r + "," + c);
                visited.add(r + "," + c);
                total += dfs(visited, stk, m, n);
            }
        }
        return total;
    }

    private void pushIfValid(int r, int c, Stack<String> stk, String pop, Set<String> visited) {
        if (r >= 0 && r < 3 && c >= 0 && c < 3) {
            stk.push(pop + ":" + r + "," + c);
            visited.add(r + "," + c);
        }
    }

    public static void main(String[] args) {
//        System.out.println(new AndroidUnlockPattern().numberOfPatterns(1, 1));
//        System.out.println(new AndroidUnlockPattern().numberOfPatterns(2, 2));
        System.out.println(new AndroidUnlockPattern().numberOfPatterns(3, 3));
//        System.out.println(new AndroidUnlockPattern().numberOfPatterns(4, 4));
//        System.out.println(new AndroidUnlockPattern().numberOfPatterns(5, 5));
//        System.out.println(new AndroidUnlockPattern().numberOfPatterns(6, 6));
//        System.out.println(new AndroidUnlockPattern().numberOfPatterns(7, 7));
//        System.out.println(new AndroidUnlockPattern().numberOfPatterns(8, 8));
//        System.out.println(new AndroidUnlockPattern().numberOfPatterns(9, 9));
    }
}
