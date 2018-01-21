package com.infoshare.junit.rover;

public class Rover {

    private char[] possibleDirections = {'N', 'E', 'S', 'W'};
    private int currentDirection = 0;
    private int[] currentPosition = {0, 0};
    private Field field;

    public Rover(int dimension, int startX, int startY) {
        field = new Field(dimension, startX, startY);
    }

    public String execute(String commands) {
        for (int i = 0; i < commands.length(); i++) {
            switch (commands.charAt(i)) {
                case 'R':
                    currentDirection = Math.abs(++currentDirection % 4);
                    break;
                case 'L':
                    currentDirection--;
                    if(currentDirection ==-1)
                        currentDirection = 3;
                    break;
                case 'M':
                    field.movement(currentDirection);
                    break;
            }
        }

        return field.getCurrentPosition()[0] + ":" +
                field.getCurrentPosition()[1] + ":" +
                possibleDirections[currentDirection];
    }


}
