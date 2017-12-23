package org.pdiploramus.nestedInteger;

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerImpl implements NestedInteger {

    private Integer i;
    private List<NestedInteger> l;
    private boolean isInteger;

    NestedIntegerImpl(Integer i) {
        this.i = i;
        this.isInteger = true;
    }

    NestedIntegerImpl() {
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
    public void setInteger(int value) {
        i = value;
    }

    @Override
    public void add(NestedInteger ni) {
        isInteger = false;
        if (l == null) {
            l = new ArrayList<>();
        }
        l.add(ni);
    }

    @Override
    public List<NestedInteger> getList() {
        return l;
    }
}
