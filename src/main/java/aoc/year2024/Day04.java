package aoc.year2024;

import aoc.Day;

import java.util.List;

public class Day04 implements Day {
    @Override
    public String part1(List<String> input) {
        var numOfXmas = 0;
        var matrix = new char[input.size()][input.getFirst().length()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = input.get(i).charAt(j);
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'X') {
                    numOfXmas += getNumOfXmas(i, j, matrix);
                }
            }
        }
        return String.valueOf(numOfXmas);
    }


    private int getNumOfXmas(int i, int j, char[][] matrix) {
        var numOfXmas = 0;
        var matrixColumnLength = matrix[0].length;
        var matrixRowLength = matrix.length;
        var downLen = i + 3 < matrixRowLength;
        var upLen = i - 3 >= 0;
        var rightLen = j + 3 < matrixColumnLength;
        var leftLen = j - 3 >= 0;

        // horizontally: j+-
        if (rightLen && matrix[i][j + 1] == 'M' && matrix[i][j + 2] == 'A' && matrix[i][j + 3] == 'S') {
            numOfXmas++;
        }
        if (leftLen && matrix[i][j - 1] == 'M' && matrix[i][j - 2] == 'A' && matrix[i][j - 3] == 'S') {
            numOfXmas++;
        }
        // vertically: i +-
        if (downLen && matrix[i + 1][j] == 'M' && matrix[i + 2][j] == 'A' && matrix[i + 3][j] == 'S') {
            numOfXmas++;
        }
        if (upLen && matrix[i - 1][j] == 'M' && matrix[i - 2][j] == 'A' && matrix[i - 3][j] == 'S') {
            numOfXmas++;
        }
        // diagonal: i/j +-
        if (rightLen && downLen && matrix[i + 1][j + 1] == 'M' && matrix[i + 2][j + 2] == 'A' && matrix[i + 3][j + 3] == 'S') {
            numOfXmas++;
        }
        if (leftLen && upLen && matrix[i - 1][j - 1] == 'M' && matrix[i - 2][j - 2] == 'A' && matrix[i - 3][j - 3] == 'S') {
            numOfXmas++;
        }
        // diagonal: i-/j+ / i+/j-
        if (rightLen && upLen && matrix[i - 1][j + 1] == 'M' && matrix[i - 2][j + 2] == 'A' && matrix[i - 3][j + 3] == 'S') {
            numOfXmas++;
        }
        if (leftLen && downLen && matrix[i + 1][j - 1] == 'M' && matrix[i + 2][j - 2] == 'A' && matrix[i + 3][j - 3] == 'S') {
            numOfXmas++;
        }
        return numOfXmas;
    }

    @Override
    public String part2(List<String> input) {
        var numOfXmas = 0;
        var matrix = new char[input.size()][input.getFirst().length()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = input.get(i).charAt(j);
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'A' && isXMas(i, j, matrix)) {
                    numOfXmas++;
                }
            }
        }
        return String.valueOf(numOfXmas);
    }


    private boolean isXMas(int i, int j, char[][] matrix) {
        var matrixColumnLength = matrix[0].length;
        var matrixRowLength = matrix.length;
        var downLen = i + 1 < matrixRowLength;
        var upLen = i - 1 >= 0;
        var rightLen = j + 1 < matrixColumnLength;
        var leftLen = j - 1 >= 0;

        var leftUpRightDown = false;
        var leftDownRightUp = false;
        if (upLen && leftLen && downLen && rightLen) {
            if ((matrix[i - 1][j - 1] == 'M' && matrix[i + 1][j + 1] == 'S') || (matrix[i - 1][j - 1] == 'S' && matrix[i + 1][j + 1] == 'M')) {
                leftUpRightDown = true;
            }
            if ((matrix[i + 1][j - 1] == 'M' && matrix[i - 1][j + 1] == 'S') || (matrix[i + 1][j - 1] == 'S' && matrix[i - 1][j + 1] == 'M')) {
                leftDownRightUp = true;
            }
        }

        return leftUpRightDown && leftDownRightUp;
    }

}
