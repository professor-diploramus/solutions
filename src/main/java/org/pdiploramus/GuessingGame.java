package org.pdiploramus;

public class GuessingGame {
    private int g;

    GuessingGame(int guess) {
        this.g = guess;
    }

    private int guess(int aguess) {
        return Integer.compare(g, aguess);
    }

    public int guessNumber(int n) {
        int l = 1, u = n;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int g = guess(mid);
            if (g == 0) return mid;
            else if (g < 0) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1000;
    }


    public static void main(String[] args) {
        GuessingGame game = new GuessingGame(60);
        System.out.println(game.guessNumber(100));
    }
}
