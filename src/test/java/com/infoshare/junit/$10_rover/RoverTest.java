package com.infoshare.junit.$10_rover;

import com.infoshare.junit.rover.Rover;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 1. tylko obrot
 * 2. ruch bez obrotu
 * 3. R i L i M jednokrotnie
 * 4. R/L>4
 * 5. przekroczenie rozmiaru siatki
 * 6.
 */

@RunWith(JUnitParamsRunner.class)
public class RoverTest {
    Rover rover;

    @Before
    public void setup() {
        rover = new Rover(10,  0, 0);
    }

    @Test
    @Ignore
    public void should_turn_right_once() {
        String position = rover.execute("R");
        assertThat(position).isEqualTo("0:0:E");
    }

    @Test
    @Ignore
    public void should_turn_right_twice() {
        String position = rover.execute("R");
        assertThat(position).isEqualTo("0:0:E");
    }

    // tests above can be simplified using JUnitParams plugin

    @Test
    @Parameters({
            "R, 0:0:E",
            "RR, 0:0:S",
            "RRL, 0:0:E"
    })
    public void should_turn_right(String commands, String position) {
        String result = rover.execute(commands);
        assertThat(result).isEqualTo(position);
    }

    @Test
    @Parameters({
            "MM, 0:2:N"
    })
    public void should_move_forward(String comands, String position) {
        String result = rover.execute((comands));
        assertThat(result).isEqualToIgnoringCase(position);
    }
    @Test
    @Parameters({
            "RM, 1:0:E",
            "RMMM, 3:0:E",
            "RMMLM, 2:1:N"
    })
    public void should_turn_right_and_move_forward(String comands, String position) {
        String result = rover.execute((comands));
        assertThat(result).isEqualToIgnoringCase(position);
    }
    @Test
    @Parameters({
            "RRRR, 0:0:N",
            "RRRRR, 0:0:E",
            "LLLLLL, 0:0:S",
            "LLLLLLRRRR, 0:0:S",
    })
    public void should_turn_more_than_360(String comands, String position) {
        String result = rover.execute((comands));
        assertThat(result).isEqualToIgnoringCase(position);
    }
    @Test
    @Parameters({
            "MMMMMMMMMM, 0:0:N",
            "RMMMMMMMMMM, 0:0:E",
            "RMMMMMMMMMML, 0:0:N",
            "RMMMMMMMMMMR, 0:0:S",
            "LMM, 8:0:W",
            "RMMLMMRRMMM, 2:9:S"

    })
    public void shouldnt_move_out_of_grid(String comands, String position) {
        String result = rover.execute((comands));
        assertThat(result).isEqualToIgnoringCase(position);
    }
}
