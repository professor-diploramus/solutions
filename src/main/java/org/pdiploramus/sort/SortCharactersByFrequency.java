package org.pdiploramus.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

 */
public class SortCharactersByFrequency {

  public String frequencySort(String s) {
    class Node {
      char c;
      int n;

      Node(char c, int n) {
        this.c = c;
        this.n = n;
      }
    }
    Map<Character, Node> freq = new HashMap<>();
    PriorityQueue<Node> pq =
        new PriorityQueue<>(
            s.length(),
            new Comparator<Node>() {
              @Override
              public int compare(Node o1, Node o2) {
                return Integer.compare(o2.n, o1.n);
              }
            });
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (!freq.containsKey(c)) {
        freq.put(c, new Node(c, 0));
      }
      Node n = freq.get(c);
      pq.remove(n);
      ++n.n;
      pq.offer(n);
    }
    StringBuilder sb = new StringBuilder();
    while (pq.size() > 0) {
      Node n = pq.poll();
      for (int i = 1; i <= n.n; i++) {
        sb.append(n.c);
      }
    }
    return sb.toString();
  }

  public String frequencySort2(String s) {
    class Node {
      char c;
      int n;

      Node(char c, int n) {
        this.c = c;
        this.n = n;
      }
    }
    Map<Character, Node> freq = new HashMap<>();
    TreeSet<Node> set =
        new TreeSet<>(
            new Comparator<Node>() {
              @Override
              public int compare(Node o1, Node o2) {
                int n = Integer.compare(o2.n, o1.n);
                if (n == 0) {
                  return Character.compare(o1.c, o2.c);
                } else {
                  return n;
                }
              }
            });
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (!freq.containsKey(c)) {
        freq.put(c, new Node(c, 0));
      }
      Node n = freq.get(c);
      set.remove(n);
      ++n.n;
      set.add(n);
    }
    StringBuilder sb = new StringBuilder();
    for (Node n : set) {
      for (int i = 1; i <= n.n; i++) {
        sb.append(n.c);
      }
    }
    return sb.toString();
  }

  @Test
  public void test() {
    Assert.assertEquals("eert", new SortCharactersByFrequency().frequencySort("eetr"));
    Assert.assertEquals("cccaaa", new SortCharactersByFrequency().frequencySort("cccaaa"));
    Assert.assertEquals("bbAa", new SortCharactersByFrequency().frequencySort("Aabb"));
  }
}
