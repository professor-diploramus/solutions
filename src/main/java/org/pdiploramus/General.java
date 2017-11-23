package org.pdiploramus;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class General {
    public static void main(String[] args) {
        Set<Set<Integer>> visited = new HashSet<>();
        Set<Integer> s1 = new HashSet<>(Arrays.asList(1,2,3));
        visited.add(s1);
        Set<Integer> s2 = new HashSet<>(Arrays.asList(1,21,3));
        visited.add(s2);
        Set<Integer> s3 = new HashSet<>(Arrays.asList(3,2,11));
        visited.add(s3);
        System.out.println(visited.size());

    }
}
