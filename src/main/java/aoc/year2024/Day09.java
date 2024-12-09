package aoc.year2024;

import aoc.Day;

import java.util.ArrayList;
import java.util.List;

public class Day09 implements Day {
    @Override
    public String part1(List<String> input) {
        var string = input.getFirst();
        var fileId = 0;
        var stringToParse = new ArrayList<String>();
        readFilesAndFreeSpace(string, stringToParse, fileId);


        // fill free space with files
        for (int i = stringToParse.size() - 1; i >= 0; i--) {
            var indexOfFirstPoint = stringToParse.indexOf(".");
            var currentString = stringToParse.get(i);
            if (!currentString.equals(".") && i > indexOfFirstPoint) {
                stringToParse.set(indexOfFirstPoint, currentString);
                stringToParse.set(i, ".");
            }
        }

        long checksum = 0;
        for (int i = 0; i < stringToParse.indexOf("."); i++) {
            var currentString = stringToParse.get(i);
            if (!currentString.equals(".")) {
                checksum += (long) i * Long.parseLong(currentString);
            }
        }
        return String.valueOf(checksum);
    }

    // read file and replace file and free space with corresponding value
    private void readFilesAndFreeSpace(String string, List<String> stringToParse, int fileId) {
        for (int i = 0; i < string.length(); i++) {
            var currentChar = string.charAt(i);
            var isCurrentCharFile = i % 2 == 0;
            var currentCharAsNum = Character.getNumericValue(currentChar);
            for (int j = 0; j < currentCharAsNum; j++) {
                if (isCurrentCharFile) {
                    stringToParse.add(String.valueOf(fileId));
                } else {
                    stringToParse.add(".");
                }
            }
            if (isCurrentCharFile) {
                fileId++;
            }
        }
    }

    @Override
    public String part2(List<String> input) {
       return null;
    }
}
