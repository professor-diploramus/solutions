package org.pdiploramus;

/**
 *
 */
public class NimGame {

    static boolean canWin(int n) {
        for (int i=1; i<=3; i++) {
            int rem = n - i;
            if (rem >= 0 && canWin(rem)) return false;
        }
        return true;
    }

    static int findWinningMove(int N) {
        for (int i=1; i<=3; i++) {
            int rem = N - i;
            if (canWin(rem)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findWinningMove(7));
    }
}
