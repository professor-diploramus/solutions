package org.pdiploramus;

public class IntegerReplacement {
  public int integerReplacement(int n) {
    return helper(n, 0);
  }
  private int helper(long n, int level) {
    if (n == 1) return level;
    if (n %2 == 0) return helper(n/2, level + 1);
    else {
      return Math.min(helper(n+1, level+1), helper(n-1, level+1));
    }
  }
  public static void main(String[] args) {
    System.out.println(new IntegerReplacement().integerReplacement(Integer.MAX_VALUE));
  }
}
