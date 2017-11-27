package org.pdiploramus;

import java.util.*;

/**
 *
 */
public class GeneralizedAbbeviation {

    public List<String> generateAbbreviations(String word) {
        List<String> ret = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        visited.add(word);
        Stack<String> stk = new Stack<>();
        stk.push(word);
        while (!stk.isEmpty()) {
            String w = stk.pop();
            ret.add(w);
            for (int i = 0; i < w.length(); i++) {
                if (isNumeral(w.charAt(i))) continue;
                String pre = w.substring(0, i);
                String suf = w.substring(i + 1);
                char left = nullOrEmpty(pre) ? '$' : pre.charAt(pre.length() - 1);
                char right = nullOrEmpty(suf) ? '$' : suf.charAt(0);
                String neww = "";
                if (!isNumeral(left) && !isNumeral(right)) {
                    neww = pre + '1' + suf;
                } else if (isNumeral(left) && !isNumeral(right)) {
                    String pre1 = pre.substring(0, pre.length() - 1);
                    int toAdd = (left - '0') + 1;
                    neww = pre1 + toAdd + suf;
                } else if (!isNumeral(left) && isNumeral(right)) {
                    String suf1 = suf.substring(1);
                    int toAdd = 1 + (right - '0');
                    neww = pre + toAdd + suf1;
                } else { // both numeral
                    String pre1 = pre.substring(0, pre.length() - 1);
                    String suf1 = suf.substring(1);
                    int toAdd = (left - '0') + 1 + (right - '0');
                    neww = pre1 + toAdd + suf1;
                }
                if (!visited.contains(neww)) {
                    visited.add(neww);
                    stk.push(neww);
                }
            }
        }
        return ret;
    }

    boolean isNumeral(char c) {
        int i = c - '0';
        return (i >= 0 && i <= 9);
    }

    boolean nullOrEmpty(String s) {
        return (s == null || s.length() == 0);
    }

    public static void main(String[] args) {
        System.out.println(new GeneralizedAbbeviation().generateAbbreviations("word"));
    }
}
