package org.pdiploramus;

import java.util.*;

public class MiniminGeneticMutation {

  // slightly enhanced to print the "path"
  public int minMutation(String start, String end, String[] bank) {
    Set<String> visited = new HashSet<>();
    visited.add(start);
    List<Character> alphabets = Arrays.asList('A', 'C', 'T', 'G');
    Set<String> sbank = new HashSet<>(Arrays.asList(bank));
    Queue<String> q = new LinkedList<>();
    q.offer(":" + start);
    while (!q.isEmpty()) {
      String pop = q.poll();
      int n = 0, lastidx = 0;
      for (int i = 0; i < pop.length(); i++) {
        if (pop.charAt(i) == ':') {
          lastidx = i;
          ++n;
        }
      }
      String s = pop.substring(lastidx + 1);
      if (s.equals(end)) {
        System.out.println(pop);
        return n - 1;
      }
      for (int i = 0; i < s.length(); i++) {
        char cToAvoid = s.charAt(i);
        String pre = s.substring(0, i);
        String suf = s.substring(i + 1);
        for (char c : alphabets) {
          if (c != cToAvoid) {
            String s1 = pre + c + suf;
            if (sbank.contains(s1)) {
              if (!visited.contains(s1)) {
                visited.add(s1);
                q.offer(pop + ":" + s1);
              }
            }
          }
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(
        new MiniminGeneticMutation()
            .minMutation(
                "AAAAACCC", "AACCCCCC", new String[] {"AAAACCCC", "AAACCCCC", "AACCCCCC"}));
  }
}
