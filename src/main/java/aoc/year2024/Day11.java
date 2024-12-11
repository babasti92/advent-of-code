package aoc.year2024;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import aoc.Day;

public class Day11 implements Day {
  @Override
  public String part1(List<String> input) {
    var inputList = Arrays.stream(input.getFirst().split(" ")).map(BigInteger::new).toList();
    var resultList = new ArrayList<BigInteger>();

    var blinkNumber = 0;
    while (blinkNumber < 25) {
      for (int i = 0; i < inputList.size(); i++) {
        var stone = inputList.get(i);
        resultList.addAll(getStonesFromStone(stone));
      }
      inputList = new ArrayList<>(resultList);
      resultList.clear();
      blinkNumber++;
    }
    return String.valueOf(inputList.size());
  }

  @Override
  public String part2(List<String> input) {
    var inputs = Arrays.stream(input.getFirst().split(" ")).map(BigInteger::new).toList();
    var inputList = new LinkedList<>(inputs);
    var resultList = new LinkedList<BigInteger>();

    var blinkNumber = 0;
    while (blinkNumber < 50) {
      for (BigInteger stone : inputList) {
        resultList.addAll(getStonesFromStone(stone));
      }
      inputList = resultList;
      blinkNumber++;
    }
    return String.valueOf(inputList.size());
  }

  private List<BigInteger> getStonesFromStone(BigInteger stone) {
    if (stone.equals(BigInteger.ZERO)) {
      return List.of(BigInteger.ONE);
    }
    var intAsString = String.valueOf(stone);
    if (intAsString.length() % 2 == 0) {
      var middleIndex = intAsString.length() / 2;
      return List.of(new BigInteger(intAsString.substring(0, middleIndex)), new BigInteger(intAsString.substring(middleIndex)));
    }
    return List.of(stone.multiply(BigInteger.valueOf(2024L)));
  }
}
