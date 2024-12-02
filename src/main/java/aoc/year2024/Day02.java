package aoc.year2024;

import aoc.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 implements Day {
    @Override
    public String part1(List<String> input) {
        return String.valueOf(input.stream().filter(entry -> isLevelSafe(false, entry)).count());
    }

    @Override
    public String part2(List<String> input) {
        return String.valueOf(input.stream().filter(entry -> isLevelSafe(true, entry)).count());
    }

    private boolean isLevelSafe(boolean checkAllPossibleVariations, String report) {
        var levels = Arrays.stream(report.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        if (!checkAllPossibleVariations) {
            return checkReport(levels);
        } else {
            return checkAllPossibleReports(levels);
        }
    }

    private boolean isIncreasing(List<Integer> integers) {
        return integers.get(1) > integers.get(0);
    }

    private boolean checkReport(List<Integer> levels) {
        var isIncreasing = isIncreasing(levels);
        for (int i = 1; i < levels.size(); i++) {
            var diff = levels.get(i) - levels.get(i - 1);
            if (isIncreasing && diff < 1 || diff > 3) {
                return false;
            } else if (!isIncreasing && diff > -1 || diff < -3) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAllPossibleReports(List<Integer> levels) {
        if (checkReport(levels)) {
            return true;
        }
        for (int i = 0; i < levels.size(); i++) {
            var removedElement = levels.remove(i);
            var validReport = checkReport(levels);
            if (validReport) {
                return true;
            }
            levels.add(i, removedElement);
        }
        return false;
    }
}
