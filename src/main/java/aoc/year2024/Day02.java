package aoc.year2024;

import java.util.Arrays;
import java.util.List;

import aoc.Day;

public class Day02 implements Day {
  @Override
  public String part1(List<String> input) {
    var numOfSafeReports = 0;
    input.forEach(entry -> {
      var levels = Arrays.stream(entry.split("\\s+")).map(level -> Integer.parseInt(level)).toList();
      if (levels.size() >= 2) {
        if (isIncreasing(levels)) {

        }
      }
    });

    return String.valueOf(sum);
  }

  @Override
  public String part2(List<String> input) {
    return "";
  }

  private boolean isIncreasing(List<Integer> integers) {
    return integers.get(1) > integers.get(0);
  }

  private boolean areNumsIncreasing(List<Integer> integers) {

  }

  private boolean isLevelSafe(boolean isIncreasing, List<Integer> integers) {
    for (int i = 1; i < integers.size(); i++) {
      var diff = integers.get(i) - integers.get(i - 1);
      switch (isIncreasing) {
      case true -> if (diff < 1 || diff > 3) {
        return false;
      }
      case false -> if(diff)
      }

    }
    return true;
  }
}
