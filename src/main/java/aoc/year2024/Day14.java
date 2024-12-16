package aoc.year2024;

import aoc.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Day14 implements Day {
    @Override
    public String part1(List<String> input) {
        // adjust for test/app
        var rows = 103;
        var columns = 101;
        // var rows = 7;
        // var columns = 11;

        var currentPositions = new ArrayList<Coordinates>();
        var velocity = new ArrayList<Coordinates>();

        input.forEach(line -> {
            currentPositions.add(getCoordinatesFromString(line.substring(line.indexOf('=') + 1, line.indexOf(" "))));
            velocity.add(getCoordinatesFromString(line.substring(line.lastIndexOf('=') + 1)));
        });

        var seconds = 100;

        for (int i = 0; i < seconds; i++) {
            for (int j = 0; j < currentPositions.size(); j++) {
                moveRobot(j, currentPositions, velocity, columns, rows);
            }
        }

        var firstQuadrantTopLeft = new Coordinates(0, 0);
        var firstQuadrantBottomRight = new Coordinates(Math.floorDiv(columns, 2) - 1, Math.floorDiv(rows, 2) - 1);
        var secondQuadrantTopLeft = new Coordinates(Math.ceilDiv(columns, 2), 0);
        var secondQuadrantBottomRight = new Coordinates(columns - 1, Math.floorDiv(rows, 2) - 1);
        var thirdQuadrantTopLeft = new Coordinates(0, Math.ceilDiv(rows, 2));
        var thirdQuadrantBottomRight = new Coordinates(Math.floorDiv(columns, 2) - 1, rows - 1);
        var forthQuadrantTopLeft = new Coordinates(Math.ceilDiv(columns, 2), Math.ceilDiv(rows, 2));
        var forthQuadrantBottomRight = new Coordinates(columns - 1, rows - 1);

        var numOfRobotsInFirst = currentPositions.stream()
                .filter(position -> isIndexInRange(position, firstQuadrantTopLeft, firstQuadrantBottomRight))
                .toList().size();
        var numOfRobotsInSecond = currentPositions.stream()
                .filter(position -> isIndexInRange(position, secondQuadrantTopLeft, secondQuadrantBottomRight))
                .toList().size();
        var numOfRobotsInThird = currentPositions.stream()
                .filter(position -> isIndexInRange(position, thirdQuadrantTopLeft, thirdQuadrantBottomRight))
                .toList().size();
        var numOfRobotsInForth = currentPositions.stream()
                .filter(position -> isIndexInRange(position, forthQuadrantTopLeft, forthQuadrantBottomRight))
                .toList().size();

        return String.valueOf(numOfRobotsInFirst * numOfRobotsInSecond * numOfRobotsInThird * numOfRobotsInForth);
    }

    @Override
    public String part2(List<String> input) {
        // adjust for test/app
        var rows = 103;
        var columns = 101;

        var currentPositions = new ArrayList<Coordinates>();
        var velocity = new ArrayList<Coordinates>();

        input.forEach(line -> {
            currentPositions.add(getCoordinatesFromString(line.substring(line.indexOf('=') + 1, line.indexOf(" "))));
            velocity.add(getCoordinatesFromString(line.substring(line.lastIndexOf('=') + 1)));
        });

        var seconds = 0;
        var distinctPositions = new HashSet<>(currentPositions);

        while (distinctPositions.size() != currentPositions.size()) {
            for (int j = 0; j < currentPositions.size(); j++) {
                moveRobot(j, currentPositions, velocity, columns, rows);
            }
            distinctPositions = new HashSet<>(currentPositions);
            seconds++;
        }
        printCurrentPositions(seconds, currentPositions, columns, rows);
        return String.valueOf(seconds);
    }

    private Coordinates getCoordinatesFromString(String s) {
        var indexOfComma = s.indexOf(",");
        return new Coordinates(Integer.parseInt(s.substring(0, indexOfComma)), Integer.parseInt(s.substring(indexOfComma + 1)));
    }

    private void moveRobot(int currentRobot, List<Coordinates> positions, List<Coordinates> velocity, int colMax, int rowMax) {
        var currentPos = positions.get(currentRobot);
        var currentVel = velocity.get(currentRobot);
        var colAfterMoving = currentPos.getCol() + currentVel.getCol();
        var rowAfterMoving = currentPos.getRow() + currentVel.getRow();
        currentPos.setCol(adjustPosition(colAfterMoving, colMax));
        currentPos.setRow(adjustPosition(rowAfterMoving, rowMax));
    }

    private boolean isIndexInRange(Coordinates toTest, Coordinates topLeft, Coordinates bottomRight) {
        return toTest.getCol() >= topLeft.getCol() && toTest.getCol() <= bottomRight.getCol() &&
                toTest.getRow() >= topLeft.getRow() && toTest.getRow() <= bottomRight.getRow();
    }

    private int adjustPosition(int pos, int max) {
        var adjustedPos = pos;
        if (pos >= max) {
            adjustedPos = pos - max;
        } else if (pos < 0) {
            adjustedPos = max + pos;
        }
        return adjustedPos;
    }

    private void printCurrentPositions(int seconds, List<Coordinates> robotPositions, int columns, int rows) {
        System.out.println("Iteration " + seconds + ":");
        var matrix = new String[rows][columns];
        robotPositions.forEach(robot -> {
            matrix[robot.getRow()][robot.getCol()] = "X";
        });
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                var characterToPrint = matrix[i][j];
                System.out.print(characterToPrint != null ? characterToPrint : ".");
            }
            System.out.println();
        }
    }
}

class Coordinates {

    private int row;
    private int col;

    public Coordinates(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return col == that.col && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }
}
