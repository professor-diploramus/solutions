package org.pdiploramus;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class General {
    public static void main(String[] args) {
        Set<Integer> st1 = new HashSet<>();
        st1.add(1);

        Set<Integer> st2 = new HashSet<>();
        st2.add(2);
        st2.add(1);

        st1.retainAll(st2);

        System.out.println(st1);

    }
}
