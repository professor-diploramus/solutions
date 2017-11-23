package org.pdiploramus;


import java.util.*;

/*
https://leetcode.com/problems/word-pattern/description/
 */
public class WordPattern {

    static boolean wordPattern(String pattern, String str) {
        Map<Character, String> ptos = new HashMap<>();
        Map<String, Character> stop = new HashMap<>();
        String[] spl = str.split(" ");
        if (pattern.length() != spl.length) return false;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String s = spl[i];
            if (ptos.containsKey(c) && !ptos.get(c).equals(s)) return false;
            ptos.put(c, s);
            if (stop.containsKey(s) && !(stop.get(s) == c)) return false;
            stop.put(s, c);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat dog"));
        System.out.println(wordPattern("abba", "dog cat cat fish"));
        System.out.println(wordPattern("aaaa", "dog cat cat dog"));
        System.out.println(wordPattern("abba", "dog dog dog dog"));
    }

}

