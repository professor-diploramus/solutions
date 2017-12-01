package org.pdiploramus;

import java.util.List;

public class NestedIntegerImpl implements NestedInteger {

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
