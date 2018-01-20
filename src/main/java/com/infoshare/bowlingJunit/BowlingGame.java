package com.infoshare.bowlingJunit;

public class BowlingGame {
    private int points = 0;
    private int tab[] = new int[20];
    private int counter = 0;
    private int spare = 0;

    private void spare() {
        for (int i = 0; i < 20; i += 2) {
            if (isSpare(i)) {
                addBonus(i);
            }
        }
    }

    private void strike() {

        for(int i=0; i<20; i +=2){
            if(tab[i] == 10){
//                tab[i+1] = tab[i+1]*2 ;
                addBonus(i);
                addBonus(i+1);
                tab[i+1] = 0;
                counter++;
            }


        }

    }

    private void addBonus(int i) {
        tab[i + 2] = tab[i + 2] * 2;
    }

    private boolean isSpare(int i) {
        return tab[i] + tab[i + 1] == 10;
    }

    public void roll(int i) {

        tab[counter] = i;
        counter++;

    }

    public int score() {
        spare();
        strike();

        for (int i = 0; i < 20; i++) {
            points = points + tab[i];
        }
        return points;
    }


}
