package org.pdiploramus;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage {

    private Queue<Integer> q;
    private long total = 0;
    private int k;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        q = new LinkedList<>();
        k = size;
    }

    public double next(int val) {
        if (q.size() == k) {
            int p = q.poll();
            total -= p;
        }
        q.offer(val);
        total += val;
        return (double)total/(double)q.size();
    }

    public static void main(String[] args) {
        MovingAverage mv = new MovingAverage(3);
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + "," + mv.next(i));
        }
    }
}
