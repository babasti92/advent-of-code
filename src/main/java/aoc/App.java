/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package aoc;

import aoc.year2023.Day01;
import aoc.year2023.Day02;
import aoc.year2023.Day03;
import aoc.year2023.Day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class App {

    private static final Map<Integer, Day> DAYS;

    static {
        DAYS = new HashMap<>();
        DAYS.put(1, new Day01());
        DAYS.put(2, new Day02());
        DAYS.put(3, new Day03());
        DAYS.put(4, new Day04());
    }

    public static List<String> loadInput(int day, int part) {
        String paddedDay = String.valueOf(day);
        if (day < 10) {
            paddedDay = "0" + day;
        }
        String fileName = "day" + paddedDay + "part" + part + ".txt";

        try (BufferedReader r = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileName)))) {
            return r.lines().collect(toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void main(String[] args) {
        int day = 4;
        if (args.length != 0) {
            day = Integer.parseInt(args[0]);
        }

        int part = 2;
        if (args.length > 1) {
            part = Integer.parseInt(args[1]);
        }

        List<String> input = loadInput(day, part);

        String result;
        if (part == 1) {
            result = DAYS.get(day).part1(input);
        } else {
            result = DAYS.get(day).part2(input);
        }

        System.out.println(result);
    }
}
