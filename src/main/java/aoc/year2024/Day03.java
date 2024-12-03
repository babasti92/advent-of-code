package aoc.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import aoc.Day;

public class Day03 implements Day {

  @Override
  public String part1(List<String> input) {
    var regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
    var pattern = Pattern.compile(regex);
    var validMulExpressions = new ArrayList<String>();
    input.forEach(entry -> {
      var matcher = pattern.matcher(entry);
      while (matcher.find()) {
        validMulExpressions.add(matcher.group());
      }
    });
    return String.valueOf(validMulExpressions.stream().map(this::calcMul).reduce(0, Integer::sum));
  }

  @Override
  public String part2(List<String> input) {
    var enabledMuls = findEnabledMuls(input);
    return part1(enabledMuls);
  }

  private int calcMul(String mul) {
    var nums = mul.split(",");
    if (nums.length == 2) {
      var firstNum = Integer.parseInt(nums[0].replaceAll("[^\\d.]", ""));
      var secondNum = Integer.parseInt(nums[1].replaceAll("[^\\d.]", ""));
      return firstNum * secondNum;
    }
    return 0;
  }

  private List<String> findEnabledMuls(List<String> input) {
    var mulExpressions = new ArrayList<String>();
    input.forEach(entry -> {
      var splitString = entry.split("don't");
      var enabledMuls = Arrays.stream(splitString).map(string -> {
        var doInex = string.indexOf("do()");
        if (doInex > -1) {
          return string.substring(doInex);
        }
        return string;
      }).toList();
      mulExpressions.addAll(enabledMuls);
    });
    return mulExpressions;
  }
}