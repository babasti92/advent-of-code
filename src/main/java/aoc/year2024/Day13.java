package aoc.year2024;

import aoc.Day;

import java.util.List;

public class Day13 implements Day {
    @Override
    public String part1(List<String> input) {
        var numOfTokens = 0L;
        for (int i = 0; i < input.size(); i = i + 4) {
            var buttonA = input.get(i);
            var buttonB = input.get(i + 1);
            var prize = input.get(i + 2);

            var buttonAXInc = getXValueFromString(buttonA, '+');
            var buttonAYInc = getYValueFromString(buttonA, '+');
            var buttonBXInc = getXValueFromString(buttonB, '+');
            var buttonBYInc = getYValueFromString(buttonB, '+');
            var prizeX = getXValueFromString(prize, '=');
            var prizeY = getYValueFromString(prize, '=');

            var numOfPushesA = (prizeY * buttonBXInc - prizeX * buttonBYInc) / (buttonAYInc * buttonBXInc - buttonAXInc * buttonBYInc);
            var numOfPushesB = (prizeX - buttonAXInc * numOfPushesA) / buttonBXInc;
            numOfPushesA = parseDoubleToLong(numOfPushesA);
            numOfPushesB = parseDoubleToLong(numOfPushesB);

            if ((numOfPushesA <= 100 && numOfPushesB <= 100) &&
                    (numOfPushesA * buttonAXInc + numOfPushesB * buttonBXInc == prizeX) &&
                    (numOfPushesA * buttonAYInc + numOfPushesB * buttonBYInc == prizeY)) {
                var sumOfPushes = numOfPushesA * 3L + numOfPushesB;
                numOfTokens += sumOfPushes;
            }
        }
        return String.valueOf(numOfTokens);
    }

    private long getXValueFromString(String string, char characterToSearchFor) {
        var substring = string.substring(string.indexOf(characterToSearchFor) + 1, string.indexOf(','));
        return Long.parseLong(substring);
    }

    private long getYValueFromString(String string, char characterToSearchFor) {
        var substring = string.substring(string.lastIndexOf(characterToSearchFor) + 1);
        return Long.parseLong(substring);
    }

    private Long parseDoubleToLong(double numOfPushesA) {
        var epsilon = 0.000000000001d;
        var roundedDouble = Math.round(numOfPushesA);
        if (Math.abs(roundedDouble - numOfPushesA) < epsilon) {
            return roundedDouble;
        }
        return 0L;
    }

    @Override
    public String part2(List<String> input) {
        var numOfTokens = 0L;
        for (int i = 0; i < input.size(); i = i + 4) {
            var buttonA = input.get(i);
            var buttonB = input.get(i + 1);
            var prize = input.get(i + 2);

            var buttonAXInc = getXValueFromString(buttonA, '+');
            var buttonAYInc = getYValueFromString(buttonA, '+');
            var buttonBXInc = getXValueFromString(buttonB, '+');
            var buttonBYInc = getYValueFromString(buttonB, '+');
            var prizeX = getXValueFromString(prize, '=') + 10000000000000L;
            var prizeY = getYValueFromString(prize, '=') + 10000000000000L;

            var numOfPushesA = (prizeY * buttonBXInc - prizeX * buttonBYInc) / (buttonAYInc * buttonBXInc - buttonAXInc * buttonBYInc);
            var numOfPushesB = (prizeX - buttonAXInc * numOfPushesA) / buttonBXInc;
            numOfPushesA = parseDoubleToLong(numOfPushesA);
            numOfPushesB = parseDoubleToLong(numOfPushesB);

            if ((numOfPushesA * buttonAXInc + numOfPushesB * buttonBXInc == prizeX) &&
                    (numOfPushesA * buttonAYInc + numOfPushesB * buttonBYInc == prizeY)) {
                var sumOfPushes = numOfPushesA * 3L + numOfPushesB;
                numOfTokens += sumOfPushes;
            }
        }
        return String.valueOf(numOfTokens);
    }
}
