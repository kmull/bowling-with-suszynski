package com.infoshare.junit.rover;

public class Field {
    private int dimension = 10;
    private int[] currentPosition = {0, 0};


    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public Field(int dimension, int startX, int startY) {
        this.dimension = dimension;
        this.currentPosition[0] = startX;
        this.currentPosition[1] = startY;
    }

    public void movement(int currentDirection) {

        switch (currentDirection) {
            case 0:
                outOfRangeNE(1);
                break;
            case 1:
                outOfRangeNE(0);
                break;
            case 2:
                outOfRangeWS(1);
                break;
            case 3:
                outOfRangeWS(0);
                break;
        }
    }

    private void outOfRangeNE(int coordinate) {
        currentPosition[coordinate]++;
        if (currentPosition[coordinate] == dimension) {
            currentPosition[coordinate] = 0;
        }
    }

    private void outOfRangeWS(int coordinate) {
        currentPosition[coordinate]--;
        if (currentPosition[coordinate] == -1) {
            currentPosition[coordinate] = dimension-1;
        }
    }

}
