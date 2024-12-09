package year2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import aoc.App;
import aoc.year2024.Day05;
import aoc.year2024.Day09;

public class Day09Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(9, 1);

        // When
        String result = new Day09().part1(input);

        // Then
        assertEquals(String.valueOf(1928), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(5, 2);

        // When
        String result = new Day05().part2(input);

        // Then
        assertEquals(String.valueOf(123), result);
    }
}
