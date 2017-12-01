package org.pdiploramus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class NestedIterator implements Iterator<Integer> {

    List<Integer> resolvedList = new ArrayList<>();
    Integer pos = -1;

    private void resolve(List<NestedInteger> nestedList, List<Integer> resolvedList) {
        for (NestedInteger n: nestedList) {
            if (n.isInteger()) {
                resolvedList.add(n.getInteger());
            } else {
                resolve(n.getList(), resolvedList);
            }
        }
    }


    public NestedIterator(List<NestedInteger> nestedList) {
        resolve(nestedList, resolvedList);
    }

    @Override
    public Integer next() {
        if (hasNext()) return resolvedList.get(++pos);
        else throw new NoSuchElementException();
    }

    @Override
    public boolean hasNext() {
        return pos < resolvedList.size()-1;
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

        NestedIterator ni = new NestedIterator(al1);
        //System.out.println(ni.resolvedList);

        while (ni.hasNext()) {
            System.out.println(ni.next());
        }
    }

}
