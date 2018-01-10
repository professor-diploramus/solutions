package org.pdiploramus.StringSlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

public class AllAnagramsInAString {
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> ret = new ArrayList<>();
    int i = 0;
    Map<Character, Integer> charmap = new HashMap<>();
    for (int k = 0; k < p.length(); k++) {
      add(charmap, p.charAt(k));
    }
    Map<Character, Integer> map = new HashMap<>();
    for (int j = 0; j < s.length(); j++) {
      char c = s.charAt(j);
      if (!charmap.containsKey(c)) {
        i = j + 1;
        map = new HashMap<>();
      } else {
        map.put(c, map.getOrDefault(c, 0) + 1);
        while (charmap.get(c) < map.get(c)) {
          ++i;
          remove(map, s.charAt(i - 1));
        }
        if (map.equals(charmap)) {
          ret.add(i);
          ++i;
          remove(map, s.charAt(i - 1));
        }
      }
    }
    return ret;
  }

  private void add(Map<Character, Integer> map, char c) {
    map.put(c, map.getOrDefault(c, 0) + 1);
  }

  private void remove(Map<Character, Integer> map, char c) {
    map.put(c, map.get(c) - 1);
    if (map.get(c) == 0) map.remove(c);
  }

  public static void main(String[] args) {
    System.out.println(new AllAnagramsInAString().findAnagrams("cbaebabacd", "abc"));
    System.out.println(new AllAnagramsInAString().findAnagrams("abab", "ab"));
    System.out.println(new AllAnagramsInAString().findAnagrams("abaacbabc", "abc"));
  }
}
