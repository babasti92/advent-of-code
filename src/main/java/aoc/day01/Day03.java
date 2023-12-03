package aoc.day01;

import aoc.Day;

import java.util.*;

public class Day03 implements Day {
    @Override
    public String part1(List<String> input) {
        var symbols = getSymbolsFromInput(input);
        var numbers = getNumbersFromInput(input);
        var partNumbers = new ArrayList<Integer>();
        numbers.entrySet().forEach(entry -> {
            if (isPartNumber(entry, symbols.keySet())) {
                partNumbers.add(entry.getValue());
            }
        });
        return String.valueOf(partNumbers.stream().mapToInt(Integer::intValue).sum());
    }

    @Override
    public String part2(List<String> input) {
        var symbols = getSymbolsFromInput(input);
        var stars = symbols.entrySet().stream().filter(entry -> entry.getValue() == '*').map(Map.Entry::getKey).toList();
        var numbers = getNumbersFromInput(input);
        var gears = new ArrayList<Integer>();
        stars.forEach(star -> {
            var neighbours = getNeighbours(star, numbers);
            if (neighbours.size() == 2) {
                gears.add(neighbours.get(0) * neighbours.get(1));
            }
        });
        return String.valueOf(gears.stream().mapToInt(Integer::intValue).sum());
    }


    private static boolean isPartNumber(Map.Entry<Position, Integer> number, Set<Position> symbols) {
        var validPositions = calculateValidSymbolPositions(number.getKey(), false);
        final boolean[] positionIsValid = {false};
        validPositions.forEach(position -> {
            if (symbols.contains(position)) {
                positionIsValid[0] = true;
            }
        });
        return positionIsValid[0];
    }

    private static List<Position> calculateValidSymbolPositions(Position numberPosition, boolean includeIndexAfterLast) {
        var validPositions = new ArrayList<Position>();
        var lastIndex = includeIndexAfterLast ? numberPosition.lastRowIndex + 1 : numberPosition.lastRowIndex;
        validPositions.add(new Position(numberPosition.line, numberPosition.firstRowIndex - 1, numberPosition.firstRowIndex - 1));
        validPositions.add(new Position(numberPosition.line, lastIndex, lastIndex));
        for (int i = numberPosition.firstRowIndex - 1; i <= lastIndex; i++) {
            validPositions.add(new Position(numberPosition.line - 1, i, i));
            validPositions.add(new Position(numberPosition.line + 1, i, i));
        }
        return validPositions;
    }

    private static Map<Position, Character> getSymbolsFromInput(List<String> input) {
        var symbols = new HashMap<Position, Character>();
        for (int i = 0; i < input.size(); i++) {
            var line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) != '.' && !Character.isDigit(line.charAt(j))) {
                    symbols.put(new Position(i, j, j), line.charAt(j));
                }
            }
        }
        return symbols;
    }

    private static Map<Position, Integer> getNumbersFromInput(List<String> input) {
        var numbers = new HashMap<Position, Integer>();
        for (int i = 0; i < input.size(); i++) {
            var line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (Character.isDigit(line.charAt(j))) {
                    var startIndex = j;
                    while (j < line.length() && Character.isDigit(line.charAt(j))) {
                        j++;
                    }
                    numbers.put(new Position(i, startIndex, j), Integer.parseInt(line.substring(startIndex, j)));
                }
            }
        }
        return numbers;
    }

    record Position(int line, int firstRowIndex, int lastRowIndex) {
    }

    private static List<Integer> getNeighbours(Position starPosition, Map<Position, Integer> numbers) {
        var neighbours = new ArrayList<Integer>();
        var neighbourPositions = calculateValidSymbolPositions(starPosition, true);
        numbers.forEach((key, value) -> {
            var numberPositions = getNumberPositions(key);
            final boolean[] isNeighbour = {false};
            numberPositions.forEach(position -> {
                if (neighbourPositions.contains(position)) {
                    isNeighbour[0] = true;
                }
            });
            if (isNeighbour[0]) {
                neighbours.add(value);
            }
        });
        return neighbours;
    }

    private static List<Position> getNumberPositions(Position position) {
        var numberPositions = new ArrayList<Position>();
        for (int i = position.firstRowIndex; i < position.lastRowIndex; i++) {
            numberPositions.add(new Position(position.line, i, i));
        }
        return numberPositions;
    }
}
