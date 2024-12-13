package year2024;

import aoc.App;
import aoc.year2024.Day05;
import aoc.year2024.Day06;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(6);

        // When
        String result = new Day06().part1(input);

        // Then
        assertEquals(String.valueOf(41), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(6);

        // When
        String result = new Day05().part2(input);

        // Then
        assertEquals(String.valueOf(123), result);
    }
}
