package org.pdiploramus;

import java.util.*;

public class AllOne {
  private static class Node implements Comparable<Node> {
    String k;
    Integer v;

    Node(String k) {
      this.k = k;
    }

    Node(String k, Integer v) {
      this.k = k;
      this.v = v;
    }

    @Override
    public int compareTo(Node o) {
      int i = this.v.compareTo(o.v);
      if (i == 0) return this.k.compareTo(o.k);
      else return i;
    }

    @Override
    public String toString() {
      return "(" + k + "," + v + ")";
    }
  }

  private Map<String, Node> map = new HashMap<>();
  private NavigableSet<Node> set =
      new TreeSet<>(
          new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
              return o1.compareTo(o2);
            }
          });

  /** Initialize your data structure here. */
  public AllOne() {}

  /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
  public void inc(String key) {
    if (!map.containsKey(key)) {
      Node n = new Node(key, 1);
      map.put(key, n);
      set.add(n);
    } else {
      Node n = map.get(key);
      set.remove(n);
      ++n.v;
      set.add(n);
    }
  }

  /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
  public void dec(String key) {
    if (!map.containsKey(key)) return;
    Node n = map.get(key);
    set.remove(n);
    if (n.v == 1) {
      map.remove(key);
      return;
    }
    --n.v;
    set.add(n);
  }

  /** Returns one of the keys with maximal value. */
  public String getMaxKey() {
    Iterator<Node> it = set.descendingIterator();
    if (it.hasNext()) return it.next().k;
    else return "empty";
  }

  /** Returns one of the keys with Minimal value. */
  public String getMinKey() {
    Iterator<Node> it = set.iterator();
    if (it.hasNext()) return it.next().k;
    else return "empty";
  }

  public static void main(String[] args) {
    AllOne ao = new AllOne();
    ao.inc("a");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
    ao.inc("b");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
    ao.inc("b");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
    ao.inc("b");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());

    ao.inc("a");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
    ao.inc("a");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
    ao.inc("a");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());

    ao.dec("a");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
    ao.dec("a");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
        ao.dec("b");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());
        ao.dec("a");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());
    ao.dec("b");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
    ao.dec("a");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
    ao.dec("b");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
    ao.dec("a");
    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());

  }
}
