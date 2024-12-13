package year2023;

import aoc.App;
import aoc.year2023.Day03;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(3);

        // When
        String result = new Day03().part1(input);

        // Then
        assertEquals(String.valueOf(413), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(3);

        // When
        String result = new Day03().part2(input);

        // Then
        assertEquals(String.valueOf(467835), result);
    }
}
