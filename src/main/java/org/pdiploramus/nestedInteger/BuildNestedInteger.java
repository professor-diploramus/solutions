package org.pdiploramus.nestedInteger;

import java.util.Stack;

public class BuildNestedInteger {

    // [1,[4,5,[6],7]]
    public static NestedInteger buildNestedInteger(String s) {
        if (s == null || s.trim().length() == 0) return null;
        Stack<NestedInteger> stk = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '[') {
                stk.add(new NestedIntegerImpl());
                i++;
            } else if (c == ',') {
                i++;
            } else if (c == ']') {
                NestedInteger ni = stk.pop();
                if (stk.isEmpty()) {
                    return ni;
                } else {
                    stk.peek().add(ni);
                }
                i++;
            } else { // c is a numeral
                int mul = 1;
                StringBuilder sb = new StringBuilder();
                while (i < s.length() && (s.charAt(i) == '-' || isNumeral(s.charAt(i)))) {
                    if (s.charAt(i) == '-') mul = -1;
                    else sb.append(s.charAt(i));
                    i++;
                }
                NestedInteger ni = new NestedIntegerImpl(mul * Integer.parseInt(sb.toString()));
                if (stk.isEmpty()) return ni;
                else {
                    stk.peek().add(ni);
                }
            }
        }
        return null;
    }

    private static boolean isNumeral(char c) {
        int n = '9' - c;
        return (0 <= n && n <= 9);
    }

}
