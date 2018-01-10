package org.pdiploramus.codingPractice;

import java.util.Stack;

public class StringCompression {
  public int compress(char[] chars) {
    int i = 0, count = 1;
    for (int j = 1; j < chars.length; j++) {
      if (chars[j] == chars[j - 1]) {
        ++count;
      } else {
        if (count > 1) {
          chars[i++] = chars[j - 1];
          Stack<Character> stk = nToca(count);
          while (!stk.isEmpty()) {
            chars[i++] = stk.pop();
          }
          count = 1;
        } else {
          chars[i++] = chars[j - 1];
          count = 1;
        }
      }
    }
    if (count > 1) {
      chars[i++] = chars[chars.length - 1];
      Stack<Character> stk = nToca(count);
      while (!stk.isEmpty()) {
        chars[i++] = stk.pop();
      }
    } else {
      chars[i++] = chars[chars.length - 1];
    }
    return i;
  }

  Stack<Character> nToca(int n) {
    Stack<Character> stk = new Stack<>();
    while (n > 0) {
      stk.push((char) ('0' + n % 10));
      n = n / 10;
    }
    return stk;
  }

  public static void main(String[] args) {
    char[] arr = {'a', 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'c', 'c'};
    System.out.println(new StringCompression().compress(arr));
  }
}
