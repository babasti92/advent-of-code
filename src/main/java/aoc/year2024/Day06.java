package aoc.year2024;

import aoc.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Day06 implements Day {
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    @Override
    public String part1(List<String> input) {
        var columns = input.getFirst().length();
        var lines = input.size();
        var visitedFields = new HashSet<Coordinates>();

        AtomicReference<Coordinates> guardPos = new AtomicReference<>();
        var obstacles = new ArrayList<Coordinates>();

        input.forEach(line -> {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') {
                    obstacles.add(new Coordinates(input.indexOf(line), i));
                } else if (line.charAt(i) == '^') {
                    guardPos.set(new Coordinates(input.indexOf(line), i));
                }
            }
        });

        var direction = Direction.UP;
        while (guardPos.get().lineNumber < lines && guardPos.get().charPosition < columns) {
            var currentGuardPos = guardPos.get();
            visitedFields.add(currentGuardPos);
            var nextCoordinates = calcNextCoordinates(currentGuardPos, direction);
            if (obstacles.contains(nextCoordinates)) {
                direction = getNextDirection(direction);
            }
            guardPos.set(calcNextCoordinates(currentGuardPos, direction));
        }
        return String.valueOf(visitedFields.size());
    }

    private Coordinates calcNextCoordinates(Coordinates currentGuardPos, Direction direction) {
        var nextCoordinates = new Coordinates(0, 0);
        switch (direction) {
            case UP -> nextCoordinates = new Coordinates(currentGuardPos.lineNumber - 1, currentGuardPos.charPosition);
            case RIGHT ->
                    nextCoordinates = new Coordinates(currentGuardPos.lineNumber, currentGuardPos.charPosition + 1);
            case DOWN ->
                    nextCoordinates = new Coordinates(currentGuardPos.lineNumber + 1, currentGuardPos.charPosition);
            case LEFT ->
                    nextCoordinates = new Coordinates(currentGuardPos.lineNumber, currentGuardPos.charPosition - 1);
        }
        return nextCoordinates;
    }

    private record Coordinates(int lineNumber, int charPosition) {
    }

    private Direction getNextDirection(Direction curr) {
        return switch (curr) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }

    @Override
    public String part2(List<String> input) {
        return "";
    }

}
