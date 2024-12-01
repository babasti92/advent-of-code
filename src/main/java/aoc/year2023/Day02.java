package aoc.year2023;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Day02 implements Day {

    private final int MAX_BLUE = 14;
    private final int MAX_RED = 12;
    private final int MAX_GREEN = 13;

    @Override
    public String part1(List<String> input) {
        var games = getGamesOfInput(input);
        var validGames = new ArrayList<Integer>();
        games.forEach(game -> {
            AtomicBoolean gameIsValid = new AtomicBoolean(true);
            game.set.forEach(set -> {
                var numOfReds = getNumOfColor(set.indexOf(Color.RED.color), set);
                var numOfBlues = getNumOfColor(set.indexOf(Color.BLUE.color), set);
                var numOfGreens = getNumOfColor(set.indexOf(Color.GREEN.color), set);
                if (numOfBlues > MAX_BLUE || numOfGreens > MAX_GREEN || numOfReds > MAX_RED) {
                    gameIsValid.set(false);
                }
            });
            if (gameIsValid.get()) {
                validGames.add(game.id);
            }
        });
        return validGames.stream().reduce(0, Integer::sum).toString();
    }

    @Override
    public String part2(List<String> input) {
        var games = getGamesOfInput(input);
        var powerOfGames = new ArrayList<Integer>();
        games.forEach(game -> {
            var maxOfColors = new HashMap<Color, Integer>();
            maxOfColors.put(Color.RED, 0);
            maxOfColors.put(Color.BLUE, 0);
            maxOfColors.put(Color.GREEN, 0);
            game.set.forEach(set -> {
                var numOfReds = getNumOfColor(set.indexOf(Color.RED.color), set);
                var numOfBlues = getNumOfColor(set.indexOf(Color.BLUE.color), set);
                var numOfGreens = getNumOfColor(set.indexOf(Color.GREEN.color), set);
                if (numOfReds > maxOfColors.get(Color.RED)) {
                    maxOfColors.put(Color.RED, numOfReds);
                }
                if (numOfBlues > maxOfColors.get(Color.BLUE)) {
                    maxOfColors.put(Color.BLUE, numOfBlues);
                }
                if (numOfGreens > maxOfColors.get(Color.GREEN)) {
                    maxOfColors.put(Color.GREEN, numOfGreens);
                }
            });
            powerOfGames.add(maxOfColors.get(Color.RED) * maxOfColors.get(Color.BLUE) * maxOfColors.get(Color.GREEN));
        });
        return powerOfGames.stream().reduce(0, Integer::sum).toString();
    }

    private List<Game> getGamesOfInput(List<String> input) {
        var games = new ArrayList<Game>();
        input.forEach(line -> {
            var id = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.indexOf(":")));
            var set = Arrays.stream(line.substring(line.indexOf(":") + 2).split(";"))
                    .map(String::trim).collect(Collectors.toList());
            games.add(new Game(id, set));
        });
        return games;
    }

    private int getNumOfColor(int index, String set) {
        if (index == -1) {
            return 0;
        } else {
            // indices for onedigit numbers
            var startIndex = index - 2;
            var endIndex = index - 1;
            while (startIndex != 0 && set.charAt(startIndex - 1) != ' ') {
                startIndex--;
            }
            return Integer.parseInt(set.substring(startIndex, endIndex));
        }
    }

    record Game(int id, List<String> set) {
    }

    enum Color {
        RED("red"),
        BLUE("blue"),
        GREEN("green");
        private final String color;

        Color(String color) {
            this.color = color;
        }
    }
}


