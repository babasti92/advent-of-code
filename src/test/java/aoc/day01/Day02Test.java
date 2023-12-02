package aoc.day01;

import aoc.App;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(2, 1);

        // When
        String result = new Day02().part1(input);

        // Then
        assertEquals(String.valueOf(8), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(2, 2);

        // When
        String result = new Day02().part2(input);

        // Then
        assertEquals(String.valueOf(2286), result);
    }
}
