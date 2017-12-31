package org.pdiploramus;

import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {
  static class Node {
    boolean visited;
    boolean visiting;
    boolean atlantic;
    boolean pacific;
    int h;

    @Override
    public String toString() {
      return "(" + h + "," + atlantic + "," + pacific + "," + visited + ")";
    }
  }

  public List<int[]> pacificAtlantic(int[][] matrix) {
    if (matrix == null || matrix.length == 0) return new ArrayList<>();
    Node[][] nmatrix = new Node[matrix.length + 2][matrix[0].length + 2];
    for (int i = 0; i < nmatrix.length; i++) {
      for (int j = 0; j < nmatrix[0].length; j++) {
        nmatrix[i][j] = new Node();
      }
    }

    for (int i = 1; i < nmatrix.length - 1; i++) {
      for (int j = 1; j < nmatrix[0].length - 1; j++) {
        nmatrix[i][j].h = matrix[i - 1][j - 1];
      }
    }
    for (int i = 1; i < nmatrix[0].length - 1; i++) {
      nmatrix[0][i].pacific = true;
      nmatrix[0][i].visited = true;
      nmatrix[0][i].h = 0;
    }
    for (int i = 1; i < nmatrix[0].length - 1; i++) {
      nmatrix[nmatrix.length - 1][i].visited = true;
      nmatrix[nmatrix.length - 1][i].atlantic = true;
      nmatrix[nmatrix.length - 1][i].h = 0;
    }
    for (int i = 1; i < nmatrix.length - 1; i++) {
      nmatrix[i][0].visited = true;
      nmatrix[i][0].pacific = true;
      nmatrix[i][0].h = 0;
    }
    for (int i = 1; i < nmatrix.length - 1; i++) {
      nmatrix[i][nmatrix[0].length - 1].visited = true;
      nmatrix[i][nmatrix[0].length - 1].atlantic = true;
      nmatrix[i][nmatrix[0].length - 1].h = 0;
    }

    for (int i = 1; i < nmatrix.length - 1; i++) {
      for (int j = 1; j < nmatrix[0].length - 1; j++) {
        if (!nmatrix[i][j].visited) {
          dfs(nmatrix, i, j);
          nmatrix[i][j].visited = true;
        }
      }
    }

    List<int[]> ret = new ArrayList<>();
    for (int i = 1; i < nmatrix.length - 1; i++) {
      for (int j = 1; j < nmatrix[0].length - 1; j++) {
        if (nmatrix[i][j].atlantic && nmatrix[i][j].pacific) {
          ret.add(new int[] {i - 1, j - 1});
        }
      }
    }
    return ret;
  }

  private void dfs(Node[][] nmatrix, int r, int c) {
    if (r < 1
        || r > nmatrix.length - 2
        || c < 1
        || c > nmatrix[0].length - 2
        || nmatrix[r][c].visited
        || nmatrix[r][c].visiting) return;
    nmatrix[r][c].visiting = true;

    // up
    if (nmatrix[r - 1][c].h <= nmatrix[r][c].h) {
      dfs(nmatrix, r - 1, c);
    }
    // down
    if (nmatrix[r + 1][c].h <= nmatrix[r][c].h) {
      dfs(nmatrix, r + 1, c);
    }
    // right
    if (nmatrix[r][c - 1].h <= nmatrix[r][c].h) {
      dfs(nmatrix, r, c - 1);
    }
    // left
    if (nmatrix[r][c + 1].h <= nmatrix[r][c].h) {
      dfs(nmatrix, r, c + 1);
    }

    if (nmatrix[r - 1][c].h <= nmatrix[r][c].h) {
      if (nmatrix[r - 1][c].atlantic) nmatrix[r][c].atlantic = true;
      if (nmatrix[r - 1][c].pacific) nmatrix[r][c].pacific = true;
    }
    // down
    if (nmatrix[r + 1][c].h <= nmatrix[r][c].h) {
      if (nmatrix[r + 1][c].atlantic) nmatrix[r][c].atlantic = true;
      if (nmatrix[r + 1][c].pacific) nmatrix[r][c].pacific = true;
    }
    // right
    if (nmatrix[r][c - 1].h <= nmatrix[r][c].h) {
      if (nmatrix[r][c - 1].atlantic) nmatrix[r][c].atlantic = true;
      if (nmatrix[r][c - 1].pacific) nmatrix[r][c].pacific = true;
    }
    // left
    if (nmatrix[r][c + 1].h <= nmatrix[r][c].h) {
      if (nmatrix[r][c + 1].atlantic) nmatrix[r][c].atlantic = true;
      if (nmatrix[r][c + 1].pacific) nmatrix[r][c].pacific = true;
    }
    nmatrix[r][c].visiting = false;
  }
  //
  //  private void printMatrix(Node[][] nmatrix) {
  //    for (int i = 0; i < nmatrix.length; i++) {
  //      for (int j = 0; j < nmatrix[0].length; j++) {
  //        System.out.print(nmatrix[i][j] + "\t");
  //      }
  //      System.out.println();
  //    }s
  //  }

  public static void main(String[] args) {
    int[][] mat = {
      // {1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}
      {10, 10, 10}, {10, 1, 10}, {10, 10, 10}
    };
    List<int[]> l = new PacificAtlanticWaterFlow().pacificAtlantic(mat);
    for (int[] a : l) {
      System.out.println("[" + a[0] + "," + a[1] + "]");
    }
  }
}
