package year2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import aoc.App;
import aoc.year2024.Day05;
import aoc.year2024.Day09;
import aoc.year2024.Day11;

public class Day11Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(11);

        // When
        String result = new Day11().part1(input);

        // Then
        assertEquals(String.valueOf(55312), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(11);

        // When
        String result = new Day11().part2(input);

        // Then
        assertEquals(String.valueOf(2858), result);
    }
}
