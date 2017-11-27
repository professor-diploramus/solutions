package org.pdiploramus;

import java.util.*;

/**
 * inspired by Tushar's video on permutation generation
 * Generate permutations in lexicographical order
 * Note that this program prints in reverse lexicographical order
 */
public class PermutationGenerator {

    static class State {
        String perm;
        String canonical;
        int[] remaining;

        State(String p, String c, int[] r) {
            this.perm = p;
            this.canonical = c;
            this.remaining = r;
        }
    }

    List<String> generate(String s) {
        List<String> ret = new ArrayList<>();
        Stack<State> stk = new Stack<>();
        stk.push(getInitialState(s));
        while (!stk.isEmpty()) {
            State stpop = stk.pop();
            if (stpop.perm.length() == s.length()) {
                ret.add(stpop.perm);
            } else {
                for (State p : getChildrenStates(stpop)) {
                    stk.push(p);
                }
            }
        }
        return ret;
    }

    State getInitialState(String s) {
        Map<Character, Integer> mp = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!mp.containsKey(c)) {
                mp.put(c, 0);
            }
            mp.put(c, mp.get(c) + 1);
        }
        int[] arr = new int[mp.keySet().size()];
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (char c : mp.keySet()) {
            sb.append(c);
            arr[i] = mp.get(c);
            i++;
        }
        State ret = new State("", sb.toString(), arr);
        return ret;
    }

    List<State> getChildrenStates(State parent) {
        List<State> ret = new ArrayList<>();
        String perm = parent.perm;
        String canonical = parent.canonical;
        int[] remaining = parent.remaining;
        for (int i=0; i<remaining.length; i++) {
            if (remaining[i] > 0) {
                int[] remCopy = remaining.clone();
                remCopy[i] = remaining[i] - 1;
                State newst = new State(perm+canonical.charAt(i), canonical, remCopy);
                ret.add(newst);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new PermutationGenerator().generate("ABCA"));
    }
}
