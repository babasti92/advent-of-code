package year2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import aoc.App;
import aoc.year2024.Day02;
import aoc.year2024.Day03;

public class Day03Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(3);

        // When
        String result = new Day03().part1(input);

        // Then
        assertEquals(String.valueOf(161), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(3);

        // When
        String result = new Day03().part2(input);

        // Then
        assertEquals(String.valueOf(48), result);
    }
}
