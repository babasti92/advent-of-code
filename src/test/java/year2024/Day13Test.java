package year2024;

import aoc.App;
import aoc.year2024.Day13;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(13);

        // When
        String result = new Day13().part1(input);

        // Then
        assertEquals(String.valueOf(480), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(13);

        // When
        String result = new Day13().part2(input);

        // Then
        assertEquals(String.valueOf(2858), result);
    }
}
