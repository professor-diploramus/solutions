package org.pdiploramus;

import java.util.ArrayList;
import java.util.List;

/**
 * You are playing the following Flip Game with your friend:
 * Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to determine if the starting player can guarantee a win.
 * For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Follow up:
 * Derive your algorithm's runtime complexity.
 *
 * First pass - (n-1) strings.
 * For each of these, (n-1) strings..... O(n!)
 *
 * Can be sped up using memoization. Also, use char array
 */
public class FlipGameII {

    static List<String> generate(String s) {
        List<String> ret = new ArrayList<>();
        for (int i=0; i<s.length()-1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {
                ret.add(s.substring(0,i)+"--"+s.substring(i+2));
            }
        }
        return ret;
    }

    /*
    A move can win if none of the children can!
    !!! The intuition is - a String can win if no valid child can be generated !!!
    If a valid child is generated, we need to investigate more.
     */
    static boolean canWin(String s) {
        for (String s1 : generate(s)) {
            if (canWin(s1)) return false;
        }
        return true;
    }

    /*
    check for each first move!
     */
    static String check(String s) {
        for (String s1 : generate(s)) {
            if (canWin(s1)) return s1;
        }
        return "cannot win!";
    }

    public static void main(String[] args) {
        System.out.println(check("++++++"));
    }


}
