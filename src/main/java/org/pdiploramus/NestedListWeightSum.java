package org.pdiploramus;

import java.util.ArrayList;
import java.util.List;

public class NestedListWeightSum {

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer,
        // rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds,
        // if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds,
        // if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    static class NestedIntegerImpl implements NestedInteger {

        private Integer i;
        private List<NestedInteger> l;
        private boolean isInteger;

        NestedIntegerImpl(Integer i) {
            this.i = i;
            this.isInteger = true;
        }

        NestedIntegerImpl(List<NestedInteger> l) {
            this.l = l;
            this.isInteger = false;
        }

        @Override
        public boolean isInteger() {
            return isInteger;
        }

        @Override
        public Integer getInteger() {
            return i;
        }

        @Override
        public List<NestedInteger> getList() {
            return l;
        }
    }


    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }

    public int depthSum(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                sum += n.getInteger() * depth;
            } else {
                sum += depthSum(n.getList(), depth + 1);
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        NestedInteger n6 = new NestedIntegerImpl(6);
        NestedInteger n4 = new NestedIntegerImpl(4);
        NestedInteger n1 = new NestedIntegerImpl(1);

        List<NestedInteger> al3 = new ArrayList<>();
        al3.add(n6);
        NestedInteger l3 = new NestedIntegerImpl(al3);

        List<NestedInteger> al2 = new ArrayList<>();
        al2.add(n4);
        al2.add(l3);
        NestedInteger l2 = new NestedIntegerImpl(al2);

        List<NestedInteger> al1 = new ArrayList<>();
        al1.add(n1);
        al1.add(l2);

        System.out.println(new NestedListWeightSum().depthSum(al1));
    }

}
