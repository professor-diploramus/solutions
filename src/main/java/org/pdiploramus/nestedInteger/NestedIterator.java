package org.pdiploramus.nestedInteger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class NestedIterator implements Iterator<Integer> {

    List<Integer> resolvedList = new ArrayList<>();
    Integer pos = -1;

    private void resolve(NestedInteger nested, List<Integer> resolvedList) {
        if (nested.isInteger()) {
            resolvedList.add(nested.getInteger());
        } else {
            List<NestedInteger> l = nested.getList();
            for (NestedInteger ni : l) {
                resolve(ni, resolvedList);
            }
        }
    }

    public NestedIterator(NestedInteger nestedList) {
        resolve(nestedList, resolvedList);
    }

    @Override
    public Integer next() {
        if (hasNext()) return resolvedList.get(++pos);
        else throw new NoSuchElementException();
    }

    @Override
    public boolean hasNext() {
        return pos < resolvedList.size() - 1;
    }


    public static void main(String[] args) {
        //[1,[4,[6]]]
//        NestedInteger n6 = new NestedIntegerImpl(6);
//        NestedInteger n4 = new NestedIntegerImpl(4);
//        NestedInteger n1 = new NestedIntegerImpl(1);
//
//        NestedInteger a3 = new NestedIntegerImpl();
//        a3.add(n6);
//
//        NestedInteger a2 = new NestedIntegerImpl();
//        a2.add(n4);
//        a2.add(a3);
//
//        NestedInteger a1 = new NestedIntegerImpl();
//        a1.add(n1);
//        a1.add(a2);

        NestedInteger n = BuildNestedInteger.buildNestedInteger("[-1]");
        NestedIterator ni = new NestedIterator(n);

        while (ni.hasNext()) {
            System.out.println(ni.next());
        }
    }

}
