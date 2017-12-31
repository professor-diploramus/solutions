package org.pdiploramus;

import java.util.*;

public class EvaluateDivision {

  static class Pair {
    String s;
    double d;

    Pair(String s, double d) {
      this.s = s;
      this.d = d;
    }
  }

  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    Map<String, List<Pair>> players = new HashMap<>();
    for (int r = 0; r < equations.length; r++) {
      String[] eq = equations[r];
      double v = values[r];
      if (!players.containsKey(eq[0])) {
        players.put(eq[0], new ArrayList<>());
      }
      if (!players.containsKey(eq[1])) {
        players.put(eq[1], new ArrayList<>());
      }
      players.get(eq[0]).add(new Pair(eq[1], v));
      players.get(eq[1]).add(new Pair(eq[0], 1.0 / v));
    }

    double[] res = new double[queries.length];
    for (int r = 0; r < queries.length; r++) {
      String[] q = queries[r];
      res[r] = dfs(q[0], q[1], 1.0, players, new HashSet<>());
    }
    return res;
  }

  private double dfs(
      String s, String e, double d, Map<String, List<Pair>> players, Set<String> visited) {
    System.out.println(s + "->" + e);
    if (!players.containsKey(s) || !players.containsKey(e)) return -1.0;
    if (s.equals(e)) return d;
    visited.add(s);
    for (Pair p : players.get(s)) {
      if (!visited.contains(p.s)) {
        double res = dfs(p.s, e, d * p.d, players, visited);
        if (res != -1.0) return res;
      }
    }
    return -1.0;
  }

  public static void main(String[] args) {
    String[][] equations = {{"a", "b"}, {"b", "c"}};
    double[] values = {2.0, 3.0};
    String[][] queries = {{"a", "c"}, {"b", "c"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
    double[] res = new EvaluateDivision().calcEquation(equations, values, queries);
    for (double d : res) {
      System.out.println(d);
    }
  }
}
