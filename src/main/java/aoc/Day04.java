package aoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Day04 implements Day {
    @Override
    public String part1(List<String> input) {
        var sumOfAllScratchCards = new AtomicInteger();
        input.forEach(line -> {
            var winningNumbers = getWinningNumbers(line);
            var scratchedNumbers = getScratchedNumbers(line);
            var numOfWinningNumbers = (int) scratchedNumbers.stream().filter(winningNumbers::contains).count();
            if (numOfWinningNumbers > 0) {
                sumOfAllScratchCards.addAndGet((int) Math.pow(2, numOfWinningNumbers - 1));
            }
        });
        return String.valueOf(sumOfAllScratchCards.get());
    }

    @Override
    public String part2(List<String> input) {
        var numOfCards = new HashMap<Integer, Integer>();
        for (int i = 1; i <= input.size(); i++) {
            numOfCards.put(i, 1);
        }
        for (int i = 0; i < input.size(); ) {
            var line = input.get(i);
            var cardNumber = ++i;
            var winningNumbers = getWinningNumbers(line);
            var scratchedNumbers = getScratchedNumbers(line);
            var numOfWinningNumbers = (int) scratchedNumbers.stream().filter(winningNumbers::contains).count();
            var numOfAddedCopies = numOfCards.get(cardNumber);
            do {
                addCopyOfFutureCards(numOfCards, numOfWinningNumbers, cardNumber);
                numOfAddedCopies--;
            } while (numOfAddedCopies > 0);

        }
        return String.valueOf(numOfCards.values().stream().mapToInt(Integer::intValue).sum());
    }

    private static List<String> getWinningNumbers(String line) {
        return Arrays.stream(line.substring(line.indexOf(":") + 1, line.indexOf("|")).split(" "))
                .map(String::trim)
                .filter(number -> number.matches("\\d+"))
                .toList();
    }

    private static List<String> getScratchedNumbers(String line) {
        return Arrays.stream(line.substring(line.indexOf("|")).split(" "))
                .map(String::trim)
                .filter(number -> number.matches("\\d+"))
                .toList();
    }

    private static void addCopyOfFutureCards(Map<Integer, Integer> numOfCards, int numOfWinningNumbers, int cardNumber) {
        for (int j = 1; j <= numOfWinningNumbers; j++) {
            var nextCardsValue = 1;
            if (numOfCards.containsKey(cardNumber + j)) {
                nextCardsValue = numOfCards.get(cardNumber + j);
            }
            numOfCards.put(cardNumber + j, ++nextCardsValue);
        }
    }
}
