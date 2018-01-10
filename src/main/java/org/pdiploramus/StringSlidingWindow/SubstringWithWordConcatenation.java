package org.pdiploramus.StringSlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

Professor Diploramus: It's key to note that the words are of the same length. That makes the problem really simple.
 */
public class SubstringWithWordConcatenation {

  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> ret = new ArrayList<>();
    Map<String, Integer> refMap = new HashMap<>();
    for (String w : words) {
      refMap.put(w, refMap.getOrDefault(w, 0) + 1);
    }
    int L = words[0].length();
    int N = words.length;
    for (int i = 0; i <= s.length() - N * L; i++) {
      Map<String, Integer> map = new HashMap<>(refMap);
      int start = i;
      for (int j = 1; j <= N; j++) {
        int end = start + L - 1;
        String s1 = s.substring(start, end + 1);
        if (map.containsKey(s1)) {
          map.put(s1, map.get(s1) - 1);
          if (map.get(s1) == 0) map.remove(s1);
        } else {
          break;
        }
        if (map.size() == 0) {
          ret.add(i);
        }
        start = end + 1;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    System.out.println(
        new SubstringWithWordConcatenation()
            .findSubstring(
                "wordgoodgoodgoodbestword", new String[] {"word", "good", "best", "good"}));
  }
}
