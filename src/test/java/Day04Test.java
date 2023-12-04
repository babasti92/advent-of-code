import aoc.App;
import aoc.Day04;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    @Test
    public void testPart1() {
        // Given
        List<String> input = App.loadInput(4, 1);

        // When
        String result = new Day04().part1(input);

        // Then
        assertEquals(String.valueOf(13), result);
    }

    @Test
    public void testPart2() {
        // Given
        List<String> input = App.loadInput(4, 2);

        // When
        String result = new Day04().part2(input);

        // Then
        assertEquals(String.valueOf(30), result);
    }
}
