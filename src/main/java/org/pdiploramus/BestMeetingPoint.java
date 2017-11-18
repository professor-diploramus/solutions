package org.pdiploramus;

import java.util.*;

/**
 * Chttps://www.programcreek.com/2014/07/leetcode-best-meeting-point-java/
 */
public class BestMeetingPoint {

    static class Traversal {
        String origin;
        int r, c;

        Traversal(String o, int r, int c) {
            this.origin = o;
            this.r = r;
            this.c = c;
        }
    }

    static String bestMeetingPoint(int[][] mat) {
        Map<String, Set<String>> visited = new HashMap<>();
        Queue<Traversal> q = new LinkedList<>();
        int sz = 0;
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {
                visited.put(r + "," + c, new HashSet<>());
                if (mat[r][c] == 1) {
                    sz++;
                    q.offer(new Traversal(r + "," + c, r, c));
                    visited.get(r + "," + c).add(r + "," + c);
                }
            }
        }
        while (!q.isEmpty()) {
            Traversal p = q.poll();
            //System.out.println(p.origin + " visiting " + p.r+","+p.c);
            if (visited.get(p.r + "," + p.c).size() == sz) {
                return p.r + "," + p.c;
            }

            String newP = "";
            if (p.r > 0) {
                newP = (p.r - 1) + "," + p.c;
                Traversal t = new Traversal(p.origin, (p.r - 1), p.c);
                if (!visited.get(newP).contains(p.origin)) {
                    visited.get(newP).add(p.origin);
                    q.offer(t);
                }
            }
            if (p.r < mat.length - 1) {
                newP = (p.r + 1) + "," + p.c;
                Traversal t = new Traversal(p.origin, (p.r + 1), p.c);
                if (!visited.get(newP).contains(p.origin)) {
                    visited.get(newP).add(p.origin);
                    q.offer(t);
                }
            }
            if (p.c > 0) {
                newP = p.r + "," + (p.c - 1);
                Traversal t = new Traversal(p.origin, p.r, (p.c - 1));
                if (!visited.get(newP).contains(p.origin)) {
                    visited.get(newP).add(p.origin);
                    q.offer(t);
                }
            }
            if (p.c < mat[0].length - 1) {
                newP = p.r + "," + (p.c + 1);
                Traversal t = new Traversal(p.origin, p.r, (p.c + 1));
                if (!visited.get(newP).contains(p.origin)) {
                    visited.get(newP).add(p.origin);
                    q.offer(t);
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        int[][] mat = {
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };
        System.out.println(bestMeetingPoint(mat));
    }
}
