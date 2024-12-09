package aoc.year2024;

import java.util.List;

import aoc.Day;

public class Day09 implements Day {
  @Override
  public String part1(List<String> input) {
    var string = input.getFirst();
    var fileId = 0;
    var stringToParse = new StringBuilder();
    for (int i = 0; i < string.length(); i++) {
      var currentChar = string.charAt(i);
      var isCurrentCharFile = i % 2 == 0;
      var currentCharAsNum = Character.getNumericValue(currentChar);
      if (isCurrentCharFile) {
        stringToParse.append(String.valueOf(fileId).repeat(currentCharAsNum));
        fileId++;
      } else {
        stringToParse.append(".".repeat(currentCharAsNum));
      }
    }

    for (int i = stringToParse.length() - 1; i >= 0; i--) {
      var indexOfFirstPoint = stringToParse.indexOf(".");
      var numOfDigits = String.valueOf(fileId).length();
      var nextChar = stringToParse.charAt(i);
      var currentString = nextChar == '.' ? "." : stringToParse.substring(i - numOfDigits + 1, i + 1);
      if (!currentString.equals(".") && i > indexOfFirstPoint) {
        stringToParse.replace(indexOfFirstPoint, indexOfFirstPoint + 1, currentString);
        stringToParse.replace(i - numOfDigits, i + 1, ".");
        fileId--;
        i -= numOfDigits - 1;
      }
    }

    long checksum = 0;
    for (int i = 0; i < stringToParse.indexOf("."); i++) {
      if (stringToParse.charAt(i) != '.') {
        checksum += (long)i * Character.getNumericValue(stringToParse.charAt(i));
      }
    }
    return String.valueOf(checksum);
  }

  @Override
  public String part2(List<String> input) {
    return null;
  }
}
