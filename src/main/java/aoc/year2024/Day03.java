package aoc.year2024;

import aoc.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
        var program = new StringBuilder();
        input.forEach(program::append);
        var programString = program.toString();

        var mulExpressions = new ArrayList<String>();
        var indexOfFirstDont = programString.indexOf("don't()");
        mulExpressions.add(programString.substring(0, indexOfFirstDont));
        programString = programString.substring(indexOfFirstDont + 1);

        var splitString = programString.split("do\\(\\)");
        for (int i = 1; i < splitString.length; i++) {
            var string = splitString[i];
            var dontIndex = string.indexOf("don't()");
            if (dontIndex != -1) {
                mulExpressions.add(string.substring(0, dontIndex));
            } else {
                mulExpressions.add(string);
            }
        }
        return mulExpressions;
    }
}