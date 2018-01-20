package com.infoshare.bowlingJunit;

public class BowlingGame {
    private int points = 0;
    private int tab[] = new int[20];
    private int counter = 0;
    private int spare = 0;

    private void compareRolls() {
        for (int i = 0; i < 20; i += 2) {
            if (tab[i] + tab[i + 1] == 10) {
                tab[i + 2] = tab[i + 2] * 2;
            }


        }
    }

    public void roll(int i) {
        tab[counter] = i;
        counter++;

    }

    public int score() {
        compareRolls();


        for (int i = 0; i < 20; i++) {
            points = points + tab[i];
        }
        return points;
    }


}
