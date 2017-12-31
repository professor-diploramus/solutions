package org.pdiploramus;

/*
Given a rows x cols screen and a sentence represented by a list of words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word won't exceed 10.
1 ≤ rows, cols ≤ 20,000.


Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output:
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.


Example 2:

Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output:
2

Explanation:
a-bcd-
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.


Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output:
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.
 */
public class SentenceScreenFitting {

  public int numFits(String[] sentence, int R, int C) {
    int i = 0, r = 0, c = 0, fits = 0;
    while (r < R) {
      if (C - c >= sentence[i].length()) {
        c += sentence[i].length()+1;
        ++i;
        if (i == sentence.length) {
          ++fits;
          i = 0;
        }
      } else {
        ++r;
        c = 0;
      }
    }
    return fits;
  }

  public static void main(String[] args) {
    String[] sentence = {"abc", "de", "f"};
    int R = 4, C = 6;
    System.out.println(new SentenceScreenFitting().numFits(sentence, R, C));
  }
}
