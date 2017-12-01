package org.pdiploramus;

import java.util.HashMap;
import java.util.Map;

//http://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
public class LongestSubstringWithKUniqueChars {
    public void getLongestSubstringWithKUniqueChars(String s, int k) {
        int i = 0, j = 0;
        int maxLen = 0, maxI = -1, maxJ = -1;
        Map<Character, Integer> mp = new HashMap<>();
        while (j < s.length()) {
            char c = s.charAt(j);
            if (!mp.containsKey(c)) mp.put(c, 1);
            else mp.put(c, mp.get(c) + 1);
            if (mp.keySet().size() < k) {
                j++;
            } else if (mp.keySet().size() == k) {
                if (j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    maxI = i;
                    maxJ = j;
                }
                j++;
            } else {
                while (mp.keySet().size() > k) {
                    char ci = s.charAt(i);
                    if (mp.get(ci) == 1) {
                        mp.remove(ci);
                    } else {
                        mp.put(c, mp.get(ci) - 1);
                    }
                    i++;
                }
                if (j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    maxI = i;
                    maxJ = j;
                }
                j++;
            }
        }
        if (maxLen == 0) System.out.println("not found");
        else
            System.out.println(maxLen + ", " + s.substring(maxI, maxJ + 1));

    }

    public static void main(String[] args) {
        new LongestSubstringWithKUniqueChars().getLongestSubstringWithKUniqueChars("aabbcc", 1);
        new LongestSubstringWithKUniqueChars().getLongestSubstringWithKUniqueChars("aabbcc", 2);
        new LongestSubstringWithKUniqueChars().getLongestSubstringWithKUniqueChars("aabbcc", 3);
        new LongestSubstringWithKUniqueChars().getLongestSubstringWithKUniqueChars("aaabbb", 3);
    }
}
