package org.pdiploramus.StringSlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestStringWithoutRepeatingChars {

  public int lengthOfLongestSubstring(String s) {
    int i = 0, j = 0, longest = 0, count = 0;
    Map<Character, Integer> map = new HashMap<>();
    while (j < s.length()) {
      map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
      ++count;
      while (count > map.size()) {
        map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
        if (map.get(s.charAt(i)) == 0) map.remove(s.charAt(i));
        ++i;
        --count;
      }
      longest = Math.max(longest, j - i + 1);
      ++j;
    }
    return longest;
  }

  public static void main(String[] args) {
    System.out.println(new LongestStringWithoutRepeatingChars().lengthOfLongestSubstring("abcabcbb"));
  }
}
