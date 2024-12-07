package aoc.year2024;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Day05 implements Day {
    @Override
    public String part1(List<String> input) {
        var rules = getRules(input);
        var validUpdates = getUpdates(input, rules, true);
        return calcSumForUpdates(validUpdates);
    }

    @Override
    public String part2(List<String> input) {
        var rules = getRules(input);
        var invalidUpdates = getUpdates(input, rules, false);
        var orderedUpdates = invalidUpdates.stream().map(update -> orderUpdate(update, rules)).toList();
        return calcSumForUpdates(orderedUpdates);

    }

    private List<String[]> getRules(List<String> input) {
        return input.stream()
                .filter(line -> line.contains("|"))
                .map(line -> line.trim().split("\\|"))
                .toList();
    }

    private List<List<String>> getUpdates(List<String> input, List<String[]> rules, boolean validUpdates) {
        return input.stream()
                .filter(line -> !line.contains("|") && !line.trim().isEmpty())
                .map(line -> Arrays.stream(line.trim().split(",")).toList())
                .filter(update -> validUpdates == doesUpdateSatisfyRules(update, rules))
                .toList();
    }

    private boolean doesUpdateSatisfyRules(List<String> update, List<String[]> rules) {
        var updateValid = new AtomicBoolean(true);
        for (int i = 0; i < update.size(); i++) {
            var currentUpdate = update.get(i);
            var rulesToApply = getRulesToApply(currentUpdate, rules);
            rulesToApply.forEach(rule -> {
                var indexOfBiggerPage = update.indexOf(rule[1]);
                if (indexOfBiggerPage != -1 && indexOfBiggerPage < update.indexOf(currentUpdate)) {
                    updateValid.set(false);
                }
            });
        }
        return updateValid.get();
    }

    private List<String[]> getRulesToApply(String page, List<String[]> rules) {
        return rules.stream().filter(rule -> rule[0].equals(page)).toList();
    }

    private String calcSumForUpdates(List<List<String>> updates) {
        var sum = updates.stream()
                .map(update -> {
                    var middleIndex = update.size() / 2;
                    return update.get(middleIndex);
                })
                .map(Integer::parseInt)
                .reduce(Integer::sum);
        return sum.map(Object::toString).orElse(null);
    }

    private List<String> orderUpdate(List<String> update, List<String[]> rules) {
        var orderedUpdate = new ArrayList<>(update);
        while (!doesUpdateSatisfyRules(orderedUpdate, rules)) {
            for (int i = 0; i < orderedUpdate.size(); i++) {
                var currentUpdate = orderedUpdate.get(i);
                var rulesToApply = getRulesToApply(currentUpdate, rules);
                rulesToApply.forEach(rule -> {
                    var indexOfBiggerPage = orderedUpdate.indexOf(rule[1]);
                    var indexOfCurrentPage = orderedUpdate.indexOf(currentUpdate);
                    if (indexOfBiggerPage != -1 && indexOfBiggerPage < indexOfCurrentPage) {
                        var biggerPage = orderedUpdate.get(indexOfBiggerPage);
                        orderedUpdate.remove(indexOfBiggerPage);
                        orderedUpdate.add(indexOfCurrentPage, biggerPage);
                    }
                });
            }
        }
        return orderedUpdate;
    }
}
