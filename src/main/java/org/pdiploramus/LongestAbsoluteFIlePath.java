package org.pdiploramus;

import java.util.HashMap;
import java.util.Map;

// "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
public class LongestAbsoluteFIlePath {
  public int lengthLongestPath(String input) {
    int longest = 0;
    String longestPath = "";
    Map<Integer, String> map = new HashMap<>();
    String[] spl = input.split("\n");
    for (String s : spl) {
      int level = s.split("\t").length - 1;
      boolean isFile = false;
      if (!s.contains(".")) isFile = false;
      else if (s.lastIndexOf('.') < s.length() - 1) isFile = true;
      String path = level == 0 ? s : map.get(level - 1) + "/" + s.substring(level);
      if (!isFile) {
        map.put(level, path);
      } else {
        if (longest < path.length()) {
          longest = path.length();
          longestPath = path;
        }
      }
    }
    System.out.println(longestPath);
    return longest;
  }

  public static void main(String[] args) {
    new LongestAbsoluteFIlePath()
        .lengthLongestPath(
            "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext");
  }
}
