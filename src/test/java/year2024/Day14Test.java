package year2024;

import aoc.App;
import aoc.year2024.Day14;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(14);

        // When
        String result = new Day14().part1(input);

        // Then
        assertEquals(String.valueOf(12), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(14);

        // When
        String result = new Day14().part2(input);

        // Then
        assertEquals(String.valueOf(2858), result);
    }
}
