package year2024;

import aoc.App;
import aoc.year2024.Day10;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(10);

        // When
        String result = new Day10().part1(input);

        // Then
        assertEquals(String.valueOf(36), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(10);

        // When
        String result = new Day10().part2(input);

        // Then
        assertEquals(String.valueOf(81), result);
    }
}
