package aoc.year2023;

import aoc.Day;

import java.util.*;

public class Day01 implements Day {

    @Override
    public String part1(List<String> input) {
        var sum = input.stream().mapToInt(this::getIntFromString).sum();
        return String.valueOf(sum);
    }

    @Override
    public String part2(List<String> input) {
        var replacedLines = new ArrayList<String>();
        input.forEach(string -> {
            var numberIndexMap = new HashMap<Number, Integer>();
            Arrays.stream(Number.values()).forEach(number -> numberIndexMap.put(number, string.indexOf(number.name)));
            final String[] replacedLine = {string};
            numberIndexMap.entrySet().stream()
                    .filter(entry -> entry.getValue() != -1)
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> replacedLine[0] = replacedLine[0].replace(entry.getKey().name, entry.getKey().replaceWith));
            replacedLines.add(replacedLine[0]);
        });
        return part1(replacedLines);
    }

    private int getIntFromString(String input) {
        var numbersOnly = input.replaceAll("[^0-9]", "");
        var firstDigit = numbersOnly.charAt(0);
        if (numbersOnly.length() == 1) {
            return Integer.parseInt("" + firstDigit + firstDigit);
        }
        var lastDigit = numbersOnly.charAt(numbersOnly.length() - 1);
        return Integer.parseInt("" + firstDigit + lastDigit);
    }

    enum Number {
        ONE("one", "o1e"),
        TWO("two", "t2o"),
        THREE("three", "t3e"),
        FOUR("four", "f4r"),
        FIVE("five", "f5e"),
        SIX("six", "s6x"),
        SEVEN("seven", "s7n"),
        EIGHT("eight", "e8t"),
        NINE("nine", "n9e");

        private final String name;
        private final String replaceWith;

        Number(String name, String replaceWith) {
            this.name = name;
            this.replaceWith = replaceWith;
        }
    }
}
