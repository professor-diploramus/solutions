package org.pdiploramus;

import java.util.Stack;

public class DecodeString {

  public String decodeString(String s) {
    Stack<Integer> stki = new Stack<>();
    Stack<Character> stkc = new Stack<>();
    int i = 0;
    while (i < s.length()) {
      char c = s.charAt(i);
      if (isNumber(c)) {
        int num = 0;
        while (isNumber(s.charAt(i))) {
          num = num * 10 + (s.charAt(i) - '0');
          i++;
        }
        stki.push(num);
      } else if (c != ']') {
        stkc.push(c);
        i++;
      } else {
        int n = stki.pop();
        char cp;
        StringBuilder sb = new StringBuilder();
        while (!stkc.isEmpty() && (cp = stkc.pop()) != '[') {
          sb.append(cp);
        }
        String sbs = sb.toString();
        for (int i1 = 1; i1 <= n; i1++) {
          for (int i2 = sbs.length() - 1; i2 >= 0; i2--) {
            stkc.push(sbs.charAt(i2));
          }
        }
        i++;
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!stkc.isEmpty()) {
      sb.append(stkc.pop());
    }
    return sb.reverse().toString();
  }

  private boolean isNumber(char c) {
    int i = c - '0';
    return (0 <= i && i <= 9);
  }

  public static void main(String[] args) {
    System.out.println(new DecodeString().decodeString("10[ab]"));
  }
}
