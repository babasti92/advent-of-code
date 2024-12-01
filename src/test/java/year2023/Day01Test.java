package year2023;

import aoc.App;
import aoc.year2023.Day01;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(1, 1);

        // When
        String result = new Day01().part1(input);

        // Then
        assertEquals(String.valueOf(142), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(1, 2);

        // When
        String result = new Day01().part2(input);

        // Then
        assertEquals(String.valueOf(281), result);
    }
}
