package org.pdiploramus;

import java.util.*;

public class FindRightInterval {

  public static class Interval {
    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }

    @Override
    public String toString() {
      return "Interval{" + "start=" + start + ", end=" + end + '}';
    }
  }

  public int[] findRightInterval(Interval[] intervals) {
    NavigableMap<Interval, Integer> tm =
        new TreeMap<>(
            new Comparator<Interval>() {
              @Override
              public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
              }
            });
    for (int i = 0; i < intervals.length; i++) {
      tm.put(intervals[i], i);
    }

    int[] ret = new int[intervals.length];
    for (int i = 0; i < intervals.length; i++) {
      int end = intervals[i].end;
      Map.Entry<Interval, Integer> e = tm.ceilingEntry(new Interval(end, 0));
      ret[i] = (e == null) ? -1 : e.getValue();
    }
    return ret;
  }

  public static void main(String[] args) {
    int[] ret =
        new FindRightInterval()
            .findRightInterval(
                new Interval[] {
                  new Interval(1, 3),
                  new Interval(2, 4),
                  new Interval(3, 5),
                  new Interval(4, 6),
                  new Interval(5, 7),
                  new Interval(6, 8)
                });
    for (int i : ret) {
      System.out.print(i + ",");
    }
  }
}
