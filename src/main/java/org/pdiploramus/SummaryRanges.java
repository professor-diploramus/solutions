package org.pdiploramus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class SummaryRanges {
    public class Interval {
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
            return "[" +
                    start + "," +
                    end +
                    ']';
        }
    }

    class Node {
        Interval v;
        Node prev, next;

        Node(Interval iv) {
            this.v = iv;
        }
    }

    Node head;


    /**
     * Initialize your data structure here.
     */
    public SummaryRanges() {
        head = null;
    }

    public void addNum(int val) {
        if (head == null) {
            Interval iv = new Interval(val, val);
            head = new Node(iv);
        } else {
            Node n = head;
            while (n != null) {
                if (val >= n.v.start && val <= n.v.end) break;
                else if (val > n.v.end) {
                    if (n.next == null) { // adding at the end
                        Interval iv = new Interval(val, val);
                        Node m = new Node(iv);
                        n.next = m;
                        m.prev = n;
                        process(m);
                        break;
                    } else {
                        n = n.next;
                    }
                } else {
                    if (n == head) { // adding at the head
                        Interval iv = new Interval(val, val);
                        Node m = new Node(iv);
                        m.next = n;
                        n.prev = m;
                        head = m;
                        process(m);
                    } else { // adding in the middle
                        Interval iv = new Interval(val, val);
                        Node m = new Node(iv);
                        n.prev.next = m;
                        m.next = n;
                        m.prev = n.prev;
                        n.prev = m;
                        process(m);
                    }
                    break;
                }
            }
        }
    }

    void process(Node n) {
        Node next = n.next;
        Node prev = n.prev;
        if (prev == null && next == null) {
            return;
        } else if (prev == null) {
            if (n.v.end == next.v.start - 1) {
                n.v.end = next.v.end;
                n.next = next.next;
                if (next.next != null) {
                    next.next.prev = n;
                }
                next.next = null;
                next.prev = null;
            }
        } else if (next == null) {
            if (n.v.start == prev.v.end + 1) {
                prev.v.end = n.v.end;
                prev.next = null;
                n.prev = null;
                n.next = null;
            }
        } else {
            if (prev.v.end != (n.v.start - 1) && n.v.end != (next.v.start - 1)) {
                return;
            } else if (prev.v.end == n.v.start - 1 && n.v.end == next.v.start - 1) {
                prev.v.end = next.v.end;
                prev.next = next.next;
                if (next.next != null) {
                    next.next.prev = prev;
                }
                next.prev = null;
                next.next = null;
                n.next = null;
                n.prev = null;
            } else if (prev.v.end == n.v.start - 1) {
                prev.v.end = n.v.end;
                prev.next = next;
                next.prev = prev;
                n.next = null;
                n.prev = null;
            } else {
                next.v.start = n.v.start;
                prev.next = next;
                next.prev = prev;
                n.next = null;
                n.prev = null;
            }
        }
    }

    public List<Interval> getIntervals() {
        Node n = head;
        List<Interval> ret = new ArrayList<>();
        while (n != null) {
            ret.add(n.v);
            n = n.next;
        }
        return ret;
    }

    TreeSet<Interval> ts = new TreeSet<>(new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
            return Integer.compare(o1.start, o2.start);
        }
    });

    // 3 cases - new interval
    // does not align with prev or next
    // bridges prev and next
    // aligns with prev
    // aligns with next
    public void addNum1(int val) {
        System.out.println("adding " + val);
        Interval i = new Interval(val, val);
        ts.add(i);
        Interval next = ts.higher(i);
        Interval prev = ts.lower(i);
        if (prev == null && next == null) {
            return;
        }
        if (prev != null && next != null && prev.end == val - 1 && val + 1 == next.start) {
            Interval n = new Interval(prev.start, next.end);
            ts.remove(i);
            ts.remove(prev);
            ts.remove(next);
            ts.add(n);
            return;
        }
        if (prev != null) {
            if (val == prev.end + 1) {
                Interval n = new Interval(prev.start, val);
                ts.remove(i);
                ts.remove(prev);
                ts.add(n);
                return;
            }
            if (val <= prev.end) {
                ts.remove(i);
                return;
            }
        }
        if (next != null) {
            if (val == next.start - 1) {
                Interval n = new Interval(val, next.end);
                ts.remove(i);
                ts.remove(next);
                ts.add(n);
                return;
            }
        }
    }

    public List<Interval> getIntervals1() {
        return new ArrayList<>(ts);
    }


    public static void main(String[] args) {
        SummaryRanges sr = new SummaryRanges();

//        sr.addNum1(6);
//        System.out.println(sr.getIntervals1());
//
//        sr.addNum1(6);
//        System.out.println(sr.getIntervals1());
//
//        sr.addNum1(0);
//        System.out.println(sr.getIntervals1());
//
//        sr.addNum1(4);
//        System.out.println(sr.getIntervals1());
//
//        sr.addNum1(8);
//        System.out.println(sr.getIntervals1());
//
//        sr.addNum1(7);
//        System.out.println(sr.getIntervals1());
//
//        sr.addNum1(6);
//        System.out.println(sr.getIntervals1());
//
//        sr.addNum1(4);
//        System.out.println(sr.getIntervals1());
//
//        sr.addNum1(7);
//        System.out.println(sr.getIntervals1());
//
//        sr.addNum1(5);
//        System.out.println(sr.getIntervals1());

        sr.addNum1(1);
        System.out.println(sr.getIntervals1());

        sr.addNum1(3);
        System.out.println(sr.getIntervals1());

        sr.addNum1(7);
        System.out.println(sr.getIntervals1());

        sr.addNum1(2);
        System.out.println(sr.getIntervals1());

        sr.addNum1(6);
        System.out.println(sr.getIntervals1());

//        sr.addNum1(1);
//        System.out.println(sr.getIntervals1());
//
//        sr.addNum1(0);
//        System.out.println(sr.getIntervals1());

    }

}
