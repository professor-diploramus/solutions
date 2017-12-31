package org.pdiploramus;

import java.util.*;

public class AllOneAlt {

  private Map<String, Integer> map = new HashMap<>();
  private int N = 50000;
  private List[] arr = new List[N];
  private int max = 0, min = 0;
  private int total;


  /** Initialize your data structure here. */
  public AllOneAlt() {
    for (int i=0; i<arr.length; i++) {
      arr[i] = new LinkedList();
    }
  }

  /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
  public void inc(String key) {
    ++total;
    int addedAt = 0;
    if (!map.containsKey(key)) {
      map.put(key, 1);
      arr[1].add(key);
      addedAt = 1;
      min = 1;
    } else {
      int v = map.get(key);
      arr[v].remove(key);
      map.put(key, v+1);
      arr[v+1].add(key);
      addedAt = v+1;
      if (min == v && arr[v].size() == 0) min++;
    }
    max = Math.max(max, addedAt);

  }

  /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
  public void dec(String key) {
    --total;
    if (!map.containsKey(key)) return;
    int v = map.get(key);
    if (v == 1) {
      map.remove(key);
      arr[1].remove(key);
      if (total == 0) {
        min=0;
      }
      else {
        // this is the only part that makes the algo > O(1)
        while (arr[min].size() == 0) ++min;
      }
    } else {
      map.put(key, v-1);
      arr[v].remove(key);
      arr[v-1].add(key);
      if (min == v) --min;
    }
    if (arr[max].size() == 0) {
      --max;
    }
  }

  /** Returns one of the keys with maximal value. */
  public String getMaxKey() {
    if (arr[max].size() == 0) return "";
    else return (String)arr[max].get(0);
  }

  /** Returns one of the keys with Minimal value. */
  public String getMinKey() {
    if (arr[min].size() == 0) return "";
    else return (String)arr[min].get(0);
  }

  public static void main(String[] args) {
    AllOneAlt ao = new AllOneAlt();
    ao.inc("a");
    ao.inc("b");
    ao.inc("b");
    ao.inc("c");
    ao.inc("c");
    ao.inc("c");

    ao.dec("b");

    ao.dec("b");

    ao.dec("a");

    System.out.println(ao.getMaxKey());
    System.out.println(ao.getMinKey());
    System.out.println();


  }
}
