package org.pdiploramus;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Set<Character> st = new TreeSet<>();
        for (int i=0; i<s.length(); i++) {
            st.add(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (char c : st) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("bcabc"));
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("cbacdcbc"));
    }
}
