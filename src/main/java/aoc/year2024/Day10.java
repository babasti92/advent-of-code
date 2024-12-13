package aoc.year2024;

import aoc.Day;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day10 implements Day {
    @Override
    public String part1(List<String> input) {
        var columns = input.getFirst().length();
        var rows = input.size();
        var numbers = new Integer[rows][columns];
        var zeros = new ArrayList<Location>();
        AtomicInteger score = new AtomicInteger();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                var currentNum = Integer.parseInt(String.valueOf(input.get(i).charAt(j)));
                if (currentNum == 0) {
                    zeros.add(new Location(i, j));
                }
                numbers[i][j] = currentNum;
            }
        }

        zeros.forEach(zero -> {
            var currentLocs = List.of(zero);
            for (int i = 1; i <= 9; i++) {
                var possibleNextLocs = calcPossibleLocsForNextStep(currentLocs, rows, columns);
                var validNextLocs = new ArrayList<Location>();
                int finalI = i;
                possibleNextLocs.forEach(loc -> {
                    if (numbers[loc.row][loc.column] == finalI) {
                        validNextLocs.add(loc);
                    }
                });
                currentLocs = validNextLocs;
                if (i == 9) {
                    var distinctLocs = new HashSet<>(validNextLocs);
                    score.addAndGet(distinctLocs.size());
                }
            }
        });
        return String.valueOf(score);
    }

    private List<Location> calcPossibleLocsForNextStep(List<Location> startingPoints, int rowMax, int colMax) {
        return startingPoints.stream()
                .map(startingPoint -> calcPossibleLocsForNextStep(startingPoint, rowMax, colMax))
                .flatMap(Collection::stream)
                .toList();
    }

    private List<Location> calcPossibleLocsForNextStep(Location loc, int rowMax, int colMax) {
        var locList = new ArrayList<Location>();
        if (loc.column - 1 >= 0) {
            locList.add(new Location(loc.row, loc.column - 1));
        }
        if (loc.row - 1 >= 0) {
            locList.add(new Location(loc.row - 1, loc.column));
        }
        if (loc.column + 1 < colMax) {
            locList.add(new Location(loc.row, loc.column + 1));
        }
        if (loc.row + 1 < rowMax) {
            locList.add(new Location(loc.row + 1, loc.column));
        }
        return locList;
    }

    @Override
    public String part2(List<String> input) {
        return "";
    }

    private record Location(int row, int column) {
    }
}
