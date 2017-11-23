package org.pdiploramus;


import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 * <p>
 * Examples:
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 */
public class WordPatternII {

    /******************************************************************************/
    /*
    This is straightforward. I exhaustively break the work into subparts
    When the number of subparts == pattern.length(), I do the matching.
    */

    /******************************************************************************/


    static void wordPatternMatch_1(String pattern, String str) {
        Stack<String> stk = new Stack<>();
        for (int i = 1; i < str.length() - 1; i++) {
            String pre = str.substring(0, i);
            String suf = str.substring(i);
            stk.push(pre + ":" + suf);
        }
        while (!stk.isEmpty()) {
            String spop = stk.pop();
            String[] spopspl = spop.split(":");
            if (spopspl.length == pattern.length()) {
                if (matchHelper_1(pattern, spop)) {
                    //System.out.println(spop);
                }
            } else {
                int idx = spop.lastIndexOf(":");
                String pre = spop.substring(0, idx);
                String suf = spop.substring(idx + 1);
                for (int i = 1; i < suf.length() - 1; i++) {
                    String pre1 = suf.substring(0, i);
                    String suf1 = suf.substring(i);
                    stk.push(pre + ":" + pre1 + ":" + suf1);
                }
            }
        }
    }

    static boolean matchHelper_1(String pattern, String word) {
        String[] spl = word.split(":");
        if (pattern.length() != spl.length) return false;
        Map<Character, String> cTOs = new HashMap<>();
        Map<String, Character> sTOc = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char p = pattern.charAt(i);
            String s = spl[i];
            if (cTOs.containsKey(p) && sTOc.containsKey(s)) {
                if (sTOc.get(s) != p || !cTOs.get(p).equals(s)) {
                    return false;
                }
            } else if (cTOs.containsKey(p) && !sTOc.containsKey(s)) {
                return false;
            } else if (!cTOs.containsKey(p) && sTOc.containsKey(s)) {
                return false;
            } else {
                sTOc.put(s, p);
                cTOs.put(p, s);
            }
        }
        return true;
    }


    /******************************************************************************/
    /*
    Same concept as wordPatternMatch_1. There I was doing the match only
      at the leaf nodes. Here I'll try do the match early on as I am generating
      the nodes and prune the size of the tree.
     */
    // numSplits: number of splits of the word to use in the match
    // assuming pattern.length() > numSplits

    /******************************************************************************/

    static void wordPatternMatch_2(String pattern, String str) {
        Stack<String> stk = new Stack<>();
        for (int i = 1; i < str.length() - 1; i++) {
            String pre = str.substring(0, i);
            String suf = str.substring(i);
            stk.push(pre + ":" + suf);
        }
        while (!stk.isEmpty()) {
            String spop = stk.pop();
            String[] spopspl = spop.split(":");
            if (spopspl.length == pattern.length()) {
                if (partialMatchHelper(pattern, spop, spopspl.length)) {
                    //System.out.println(spop);
                }
            } else {
                if (partialMatchHelper(pattern, spop, spopspl.length - 1)) {
                    int idx = spop.lastIndexOf(":");
                    String pre = spop.substring(0, idx);
                    String suf = spop.substring(idx + 1);
                    for (int i = 1; i < suf.length() - 1; i++) {
                        String pre1 = suf.substring(0, i);
                        String suf1 = suf.substring(i);
                        stk.push(pre + ":" + pre1 + ":" + suf1);
                    }
                }
            }
        }
    }

    static boolean partialMatchHelper(String pattern, String word, int numSplits) {
        if (numSplits == 1) return true;
        String[] spl = word.split(":");
        Map<Character, String> cTOs = new HashMap<>();
        Map<String, Character> sTOc = new HashMap<>();
        for (int i = 0; i <= numSplits - 1; i++) {
            char p = pattern.charAt(i);
            String s = spl[i];
            if (cTOs.containsKey(p) && sTOc.containsKey(s)) {
                if (sTOc.get(s) != p || !cTOs.get(p).equals(s)) {
                    return false;
                }
            } else if (cTOs.containsKey(p) && !sTOc.containsKey(s)) {
                return false;
            } else if (!cTOs.containsKey(p) && sTOc.containsKey(s)) {
                return false;
            } else {
                sTOc.put(s, p);
                cTOs.put(p, s);
            }
        }
        return true;
    }


    /******************************************************************************/
    /*
    If pattern has p characters, string can be divided into p partitions.
    String of length n has n-1 places to place the divider. Out of that we need to
    select p-1 places to place dividers.
    In short, the string can be divided up in (n-1)C(p-1) ways.
    Then match pattern with each divided string.

    It's super important to use visited to reduce the size of the tree!
     */

    /******************************************************************************/

    static void wordPatternMatch_3(String pattern, String str) {
        int divs = pattern.length() - 1;
        int holes = str.length() - 1;
        Set<Integer> s1 = new HashSet<>();
        for (int i = 0; i < holes; i++) {
            s1.add(i);
        }
        Set<Set<Integer>> visited = new HashSet<>();
        Stack<Set<Integer>> stk = new Stack<>();
        stk.add(s1);
        visited.add(s1);
        while (!stk.isEmpty()) {
            Set<Integer> spop = stk.pop();
            if (spop.size() == divs) {
                StringBuilder divided = new StringBuilder();
                List<Integer> l = new ArrayList<>(spop);
                Collections.sort(l);
                int last = 0;
                for (int i : l) {
                    divided.append(str.substring(last, i + 1)).append(":");
                    last = i + 1;
                }
                divided.append(str.substring(last));
                String dstr = divided.toString();
                if (matchHelper_1(pattern, dstr)) {
                    //System.out.println(dstr);
                }
            } else {
                for (int r : spop) {
                    Set<Integer> s2 = new HashSet<>(spop);
                    s2.remove(r);
                    if (!visited.contains(s2)) {
                        visited.add(s2);
                        stk.add(s2);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        assertTrue(matchHelper_1("abab", "red:blue:red:blue"));
        assertFalse(matchHelper_1("abba", "dog:cat:cat:fish"));
        assertFalse(matchHelper_1("aaaa", "dog:cat:cat:dog"));
        assertFalse(matchHelper_1("abba", "dog:dog:dog:dog"));

        assertTrue(partialMatchHelper("abab", "red:blue:red:blue", 4));
        assertFalse(partialMatchHelper("abba", "dog:cat:cat:fish", 4));
        assertFalse(partialMatchHelper("aaaa", "dog:cat:cat:dog", 4));
        assertFalse(partialMatchHelper("abba", "dog:dog:dog:dog", 4));

        long t1, t2, t3, start, end;

        start = System.nanoTime();
        for (int i = 1; i <= 100; i++) {
            wordPatternMatch_1("abab", "redblueredblue");
            wordPatternMatch_1("aaaa", "asdasdasdasd");
            wordPatternMatch_1("aabb", "xyzabcxzyabc");
        }
        end = System.nanoTime();
        t1 = (end - start) / 300;

        start = System.nanoTime();
        for (int i = 1; i <= 100; i++) {
            wordPatternMatch_2("abab", "redblueredblue");
            wordPatternMatch_2("aaaa", "asdasdasdasd");
            wordPatternMatch_2("aabb", "xyzabcxzyabc");
        }
        end = System.nanoTime();
        t2 = (end - start) / 300;


        start = System.nanoTime();
        for (int i = 1; i <= 100; i++) {
            wordPatternMatch_3("abab", "redblueredblue");
            wordPatternMatch_3("aaaa", "asdasdasdasd");
            wordPatternMatch_3("aabb", "xyzabcxzyabc");
        }
        end = System.nanoTime();
        t3 = (end - start) / 300;

        System.out.println(t1 + "," + t2 + "," + t3);
        System.out.println(String.format("%.2f", (double)t1/t2) + "," + 1 + "," + String.format("%.2f", (double)t3/t2));
    }
}
