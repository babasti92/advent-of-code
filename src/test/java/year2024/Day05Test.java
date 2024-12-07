package year2024;

import aoc.App;
import aoc.year2024.Day05;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(5, 1);

        // When
        String result = new Day05().part1(input);

        // Then
        assertEquals(String.valueOf(143), result);
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
