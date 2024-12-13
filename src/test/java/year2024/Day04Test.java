package year2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import aoc.App;
import aoc.year2024.Day04;

public class Day04Test {

  @Test
  public void testPart1() {
    // Given
    List<String> input = App.loadInput(4);

    // When
    String result = new Day04().part1(input);

    // Then
    assertEquals(String.valueOf(18), result);
  }

  @Test
  public void testPart2() {
    // Given
    List<String> input = App.loadInput(4);

    // When
    String result = new Day04().part2(input);

    // Then
    assertEquals(String.valueOf(9), result);
  }
}
