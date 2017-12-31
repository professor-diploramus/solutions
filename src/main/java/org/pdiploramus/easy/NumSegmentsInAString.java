package org.pdiploramus.easy;

public class NumSegmentsInAString {
  public int countSegments(String s) {
    char last = ' ';
    int n = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (last == ' ' && c != ' ') {
        ++n;
      }
      last = c;
    }
    return n;
  }
}
