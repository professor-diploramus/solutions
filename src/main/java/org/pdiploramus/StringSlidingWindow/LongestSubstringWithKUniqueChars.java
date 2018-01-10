package org.pdiploramus.StringSlidingWindow;

import java.util.HashMap;
import java.util.Map;

// http://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
// https://www.programcreek.com/2013/02/longest-substring-which-contains-2-unique-characters/
public class LongestSubstringWithKUniqueChars {

  public String getLongestSubstringWithKUniqueChars(String s, int k) {
    String longest = "";
    int i = 0;
    Map<Character, Integer> map = new HashMap<>();
    for (int j = 0; j < s.length(); j++) {
      map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
      while (map.size() > k) {
        ++i;
        map.put(s.charAt(i - 1), map.get(s.charAt(i - 1)) - 1);
        if (map.get(s.charAt(i - 1)) == 0) map.remove(s.charAt(i - 1));
      }

      if (j - i + 1 > longest.length()) {
        longest = s.substring(i, j + 1);
      }
    }
    return longest;
  }

  public static void main(String[] args) {
    System.out.println(
        new LongestSubstringWithKUniqueChars()
            .getLongestSubstringWithKUniqueChars("abcbbbbcccbdddadacb", 2));
    System.out.println(
        new LongestSubstringWithKUniqueChars().getLongestSubstringWithKUniqueChars("aabbcc", 1));
    System.out.println(
        new LongestSubstringWithKUniqueChars().getLongestSubstringWithKUniqueChars("aabbcc", 2));
    System.out.println(
        new LongestSubstringWithKUniqueChars().getLongestSubstringWithKUniqueChars("aabbcc", 3));
    System.out.println(
        new LongestSubstringWithKUniqueChars().getLongestSubstringWithKUniqueChars("aaabbb", 3));
      System.out.println(
          new LongestSubstringWithKUniqueChars().getLongestSubstringWithKUniqueChars("eceba", 2));

  }
}
