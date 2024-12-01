package aoc.year2024;

import aoc.Day;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Day01 implements Day {
    @Override
    public String part1(List<String> input) {
        var lists = getListsFromInput(input);
        var list1 = lists.get(0);
        var list2 = lists.get(1);
        list1.sort(Comparator.comparingInt(Integer::intValue));
        list2.sort(Comparator.comparingInt(Integer::intValue));

        assert list1.size() == list2.size();
        var sum = 0;
        for (int i = 0; i < list1.size(); i++) {
            sum += Math.abs(list1.get(i) - list2.get(i));
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(List<String> input) {
        var lists = getListsFromInput(input);
        var list1 = lists.get(0);
        var list2 = lists.get(1);

        var countNums = new HashMap<Integer, Integer>();
        list1.forEach(list1Entry -> list2.forEach(list2Entry -> {
            if (list1Entry.equals(list2Entry)) {
                countNums.merge(list1Entry, 1, Integer::sum);
            }
        }));

        var similarityScore = countNums.entrySet().stream()
                .map(entry -> entry.getKey() * entry.getValue())
                .reduce(Integer::sum);
        return similarityScore.map(Object::toString).orElse("");
    }

    private List<List<Integer>> getListsFromInput(List<String> input) {
        var list1 = new ArrayList<Integer>();
        var list2 = new ArrayList<Integer>();

        input.forEach(entry -> {
            var splitLine = entry.split("\\s+");
            if (splitLine.length == 2) {
                list1.add(Integer.parseInt(splitLine[0]));
                list2.add(Integer.parseInt(splitLine[1]));
            }
        });

        return List.of(list1, list2);
    }
}
