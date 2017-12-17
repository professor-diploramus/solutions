package org.pdiploramus;

import java.util.*;

public class TopKFrequent {

    static class Pair {
        Integer element;
        Integer freq;

        Pair(int a, int b) {
            this.element = a;
            this.freq = b;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> ret = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(k + 1, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.freq.compareTo(o2.freq);
            }
        });
        Map<Integer, Pair> mp = new HashMap<>();
        for (int i : nums) {
            Pair p;
            if (!mp.containsKey(i)) {
                p = new Pair(i, 1);
                mp.put(i, p);
            } else {
                p = mp.get(i);
                ++p.freq;
            }
            pq.remove(p);
            pq.offer(p);
            if (pq.size() == k + 1) {
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            ret.add(pq.poll().element);
        }
        return ret;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 4, 2, 2, 1, 5, 6, 7, 8};
        System.out.println(new TopKFrequent().topKFrequent(arr, 3));


    }
}
