package org.pdiploramus;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
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
  }

  public int eraseOverlapIntervals(Interval[] intervals) {
    if (intervals.length == 0) return 0;

    Arrays.sort(
        intervals,
        new Comparator<Interval>() {
          @Override
          public int compare(Interval o1, Interval o2) {
            return Integer.compare(o1.end, o2.end);
          }
        });
    int end = intervals[0].end;
    int count = 1;

    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i].start >= end) {
        end = intervals[i].end;
        count++;
      }
    }
    return intervals.length - count;
  }

  public static void main(String[] args) {
    System.out.println(
        new NonOverlappingIntervals()
            .eraseOverlapIntervals(
                new Interval[] {
                  new Interval(1, 2), new Interval(2, 3), new Interval(3, 4), new Interval(1, 3)
                }));
  }
}
