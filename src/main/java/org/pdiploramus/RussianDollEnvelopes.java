package org.pdiploramus;

import java.util.*;

public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        int ret = 1;
        List<String> lst = new ArrayList<>();
        for (int[] i : envelopes) {
            lst.add(i[0] + "," + i[1]);
            lst.add(i[1] + "," + i[0]);
        }
        Collections.sort(lst, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.split(",")[0].compareTo(o2.split(",")[0]);
            }
        });
        System.out.println(lst);
        int[] max = new int[lst.size()];
        Arrays.fill(max, 1);
        for (int i = 1; i < max.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                String curr = lst.get(i);
                String prev = lst.get(j);
                String[] currSplit = curr.split(",");
                String[] prevSplit = prev.split(",");
                if (Integer.valueOf(currSplit[0]) > Integer.valueOf(prevSplit[0]) &&
                        Integer.valueOf(currSplit[1]) > Integer.valueOf(prevSplit[1])) {
                    max[i] = Math.max(max[i], max[j] + 1);
                    ret = Math.max(ret, max[i]);
                }
            }
        }
        return ret;
    } // 5,9, 4,8, 3,5, 1,3

    public static void main(String[] args) {
        int[][] e = {{1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}};
        System.out.println(new RussianDollEnvelopes().maxEnvelopes(e));
    }
}
