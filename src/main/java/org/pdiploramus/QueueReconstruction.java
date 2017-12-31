package org.pdiploramus;

import java.util.Arrays;
import java.util.Comparator;

public class QueueReconstruction {
  static class Node {
    int[] tuple;
    Node next;

    Node(int[] t) {
      this.tuple = t;
    }
  }

  public int[][] reconstructQueue(int[][] people) {
    int[][] ret = new int[people.length][people[0].length];
    Arrays.sort(
        people,
        new Comparator<int[]>() {
          @Override
          public int compare(int[] o1, int[] o2) {
            if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
            else return Integer.compare(o2[0], o1[0]);
          }
        });
    Node head = null;
    for (int[] t : people) {
      head = insert(head, t);
    }
    int i = 0;
    while (head != null) {
      ret[i++] = head.tuple;
      head = head.next;
    }
    return ret;
  }

  // {7, 0} {7, 1} {6, 1} {5, 0} {5, 2} {4, 4}
  private Node insert(Node head, int[] t) {
    Node nw = new Node(t);
    int skip = t[1];
    if (head == null) {
      head = nw;
    } else if (skip == 0) {
      Node temp = head;
      head = nw;
      nw.next = temp;
    } else {
      Node h = head;
      for (int i = 1; i < skip; i++) {
        h = h.next;
      }
      Node temp = h.next;
      h.next = nw;
      nw.next = temp;
    }
    return head;
  }

  public static void main(String[] args) {
    int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
    int[][] ret = new QueueReconstruction().reconstructQueue(people);
  }
}
