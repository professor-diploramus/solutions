package org.pdiploramus.nestedInteger;

public class NestedListWeightSum {

    public int depthSum(NestedInteger nested) {
        return depthSum(nested, 0);
    }

    public int depthSum(NestedInteger nested, int depth) {
        if (nested.isInteger()) {
            return nested.getInteger() * depth;
        } else {
            int sum = 0;
            for (NestedInteger ni : nested.getList()) {
                sum += depthSum(ni, depth+1);
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        //[1,[4,[6]]]
        NestedInteger n = BuildNestedInteger.buildNestedInteger("[1,[4,[6]]]");
        System.out.println(new NestedListWeightSum().depthSum(n));
    }

}
