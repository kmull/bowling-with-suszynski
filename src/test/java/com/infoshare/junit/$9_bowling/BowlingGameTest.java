package com.infoshare.junit.$9_bowling;

import com.infoshare.bowlingJunit.BowlingGame;
//import com.infoshare.junit.bowling.Game;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    private BowlingGame bowlingGame;

    /**
     * 1. same 0
     * 2. same strike
     * 3. same spare
     * 4. wiecej spare od 0 i mniej od 10
     * 5. wiecej strike od 0 i mniej od 12
     * 6. 10 spare + 1 strike
     * 7. 1 strike 1 spare
     */

    @Before
    public void setUp() {
         bowlingGame = new BowlingGame();
    }

    @Test
    public void failed_game() {

        for (int i = 0; i < 20; i++) {
            bowlingGame.roll(0);
        }
        assertThat(bowlingGame.score()).isEqualTo(0);
    }

    @Test
    public void poorGame() {

        for (int i = 0; i < 20; i++)
            bowlingGame.roll(1);
        assertThat(bowlingGame.score()).isEqualTo(20);

    }

    @Test
    public void randomGame() {

        bowlingGame.roll(2);
        bowlingGame.roll(5);
        bowlingGame.roll(6);
        bowlingGame.roll(8);
        bowlingGame.roll(0);
        bowlingGame.roll(0);
        assertThat(bowlingGame.score()).isEqualTo(21);
    }

    @Test
    public void oneSpare() {
        bowlingGame.roll(5);
        bowlingGame.roll(5);
        bowlingGame.roll(2);
        assertThat(bowlingGame.score()).isEqualTo(14);
    }

    @Test
    public void moreSpares() {
        bowlingGame.roll(5);
        bowlingGame.roll(5);

        bowlingGame.roll(2);
        bowlingGame.roll(2);

        bowlingGame.roll(5);
        bowlingGame.roll(5);

        bowlingGame.roll(2);
        bowlingGame.roll(3);

        assertThat(bowlingGame.score()).isEqualTo(33);
    }

    @Test
    public void oneStrike() {
        bowlingGame.roll(5);
        bowlingGame.roll(5);
        bowlingGame.roll(2);
        assertThat(bowlingGame.score()).isEqualTo(14);
    }


}
















