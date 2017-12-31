package org.pdiploramus;

/*
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:

Given s = "internationalization", abbr = "i12iz4n":

Return true.


Example 2:

Given s = "apple", abbr = "a2e":

Return false.
 */
public class ValidWordAbbreviation {
  public boolean isValid(String word, String abbr) {
    int w = 0, a = 0;
    while (w < word.length() && a < abbr.length()) {
      if (!isNum(abbr.charAt(a))) {
        if (abbr.charAt(a) == word.charAt(w)) {
          w++;
          a++;
        } else {
          return false;
        }
      } else {
        int num = abbr.charAt(a) - '0';
        ++a;
        while (a < abbr.length() && isNum(abbr.charAt(a))) {
          num = num * 10 + (abbr.charAt(a) - '0');
          ++a;
        }
        w = w + num;
      }
    }
    if (w == word.length() && a == abbr.length()) return true;
    else return false;
  }

  private boolean isNum(char c) {
    int i = c - '0';
    return (i >= 0 && i <= 9);
  }

  public static void main(String[] args) {
    System.out.println(new ValidWordAbbreviation().isValid("word", "word"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "1ord"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "w1rd"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "wo1d"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "wor1"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "2rd"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "w2d"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "wo2"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "1o1d"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "1or1"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "w1r1"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "1o2"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "2r1"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "3d"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "w3"));
    System.out.println(new ValidWordAbbreviation().isValid("word", "4"));
    System.out.println(new ValidWordAbbreviation().isValid("internationalization", "i12iz4n"));
    System.out.println(new ValidWordAbbreviation().isValid("apple", "a2e"));
  }
}
