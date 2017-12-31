package org.pdiploramus;

import java.util.HashMap;
import java.util.Map;

/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

 */
public class LongestPalindrome {

  public int longestPalindrome(String s) {
    if (s== null || s.trim().length() == 0) return 0;
    Map<Character, Integer> freq = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (!freq.containsKey(c)) {
        freq.put(c, 0);
      }
      freq.put(c, freq.get(c) + 1);
    }
    int longest = 0;
    int plusOne = 0;
    for (Map.Entry<Character, Integer> e : freq.entrySet()) {
      if (e.getValue() % 2 == 0) {
        longest += e.getValue();
      } else {
        plusOne = 1;
        longest += e.getValue() - 1;
      }
    }
    return longest + plusOne;
  }

  public static void main(String[] args) {
    System.out.println(new LongestPalindrome().longestPalindrome("abccccdd"));
  }
}
