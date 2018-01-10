package org.pdiploramus.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true

*/
public class SequenceReconstruction {

  // modified topo sort using bfs
  public boolean sequenceReconstruction(int[] org, int[][] seqs) {
    Map<Integer, Integer> indegrees = new HashMap<>();
    Map<Integer, List<Integer>> children = new HashMap<>();
    Arrays.stream(seqs)
        .forEach(
            x -> {
              indegrees.put(x[0], indegrees.getOrDefault(x[0], 0));
              children.put(x[0], children.getOrDefault(x[0], new ArrayList<>()));
              for (int i = 1; i < x.length; i++) {
                indegrees.put(x[i], indegrees.getOrDefault(x[i], 0) + 1);
                children.put(x[i], children.getOrDefault(x[i], new ArrayList<>()));
                children.get(x[i - 1]).add(x[i]);
              }
            });
    if (children.keySet().size() < org.length) return false;
    Queue<Integer> q = new LinkedList<>();
    indegrees.forEach(
        (k, v) -> {
          if (v == 0) q.offer(k);
        });
    while (!q.isEmpty()) {
      if (q.size() > 1) return false;
      Integer p = q.poll();
      children
          .get(p)
          .forEach(
              x -> {
                indegrees.put(x, indegrees.get(x) - 1);
                if (indegrees.get(x) == 0) q.offer(x);
              });
    }
    return true;
  }

  @Test
  public void test() {
    Assert.assertFalse(
        new SequenceReconstruction()
            .sequenceReconstruction(new int[] {1, 2, 3}, new int[][] {{1, 2}, {1, 3}}));

    Assert.assertFalse(
        new SequenceReconstruction()
            .sequenceReconstruction(new int[] {1, 2, 3}, new int[][] {{1, 2}}));

    Assert.assertTrue(
        new SequenceReconstruction()
            .sequenceReconstruction(new int[] {1, 2, 3}, new int[][] {{1, 2}, {1, 3}, {2, 3}}));

    Assert.assertTrue(
        new SequenceReconstruction()
            .sequenceReconstruction(
                new int[] {4, 1, 5, 2, 6, 3}, new int[][] {{5, 2, 6, 3}, {4, 1, 5, 2}}));

    Assert.assertFalse(
        new SequenceReconstruction()
            .sequenceReconstruction(new int[] {1, 2, 3, 4}, new int[][] {{1, 3}, {2, 4}}));

    Assert.assertFalse(
        new SequenceReconstruction()
            .sequenceReconstruction(new int[] {1, 2, 3, 4}, new int[][] {{1, 3}, {2, 4}, {1, 4}}));

    Assert.assertFalse(
        new SequenceReconstruction()
            .sequenceReconstruction(
                new int[] {1, 2, 3, 4}, new int[][] {{1, 3}, {2, 4}, {1, 4}, {1, 2}}));

    Assert.assertFalse(
        new SequenceReconstruction()
            .sequenceReconstruction(
                new int[] {1, 2, 3, 4}, new int[][] {{1, 3}, {2, 4}, {1, 4}, {1, 2}, {2, 3}}));

    Assert.assertTrue(
        new SequenceReconstruction()
            .sequenceReconstruction(
                new int[] {1, 2, 3, 4},
                new int[][] {{1, 3}, {2, 4}, {1, 4}, {1, 2}, {2, 3}, {3, 4}}));
  }
}
