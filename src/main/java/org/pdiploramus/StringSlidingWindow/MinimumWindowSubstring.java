package org.pdiploramus.StringSlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
  public String minWindow(String s, String t) {
    Map<Character, Integer> charmap = buildCharMap(t);
    int i = 0, j = 0;
    String minWindow = "";
    int minLength = Integer.MAX_VALUE;
    Map<Character, Integer> map = new HashMap<>();
    while (j < s.length()) {
      addToMap(map, charmap, s.charAt(j));
      while (allCharsIncluded(map, charmap)) {
        if (minLength > (j - i + 1)) {
          minLength = j - i + 1;
          minWindow = s.substring(i, j + 1);
        }
        ++i;
        removerFromMap(map, s.charAt(i - 1));
      }
      j++;
    }
    return minWindow;
  }

  private Map<Character, Integer> buildCharMap(String t) {
    Map<Character, Integer> charmap = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      char c = t.charAt(i);
      charmap.put(c, charmap.getOrDefault(c, 0) + 1);
    }
    return charmap;
  }

  private void addToMap(Map<Character, Integer> map, Map<Character, Integer> charmap, char c) {
    if (charmap.containsKey(c)) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
  }

  private void removerFromMap(Map<Character, Integer> map, char c) {
    if (map.containsKey(c)) {
      map.put(c, map.get(c) - 1);
      if (map.get(c) == 0) {
        map.remove(c);
      }
    }
  }

  // assumption - chars not in charmap will not be present in map. However, the number of
  // occurrances of a char in map may be > number of occurrances in charmap
  private boolean allCharsIncluded(Map<Character, Integer> map, Map<Character, Integer> charmap) {
    if (map.size() != charmap.size()) return false;
    for (Map.Entry<Character, Integer> e : map.entrySet()) {
      if (e.getValue() < charmap.get(e.getKey())) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
  }
}
