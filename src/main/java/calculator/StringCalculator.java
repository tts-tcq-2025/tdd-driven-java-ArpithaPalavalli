package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StringCalculator {

    private StringCalculator() {
        // Prevent instantiation - utility class
    }

    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        // Default delimiters
        List<String> delimiters = new ArrayList<>(List.of(",", "\n"));
        String numbers = input;

        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");
            delimiters.addAll(parseDelimiters(input.substring(2, delimiterEndIndex)));
            numbers = input.substring(delimiterEndIndex + 1);
        }

        return sumNumbers(numbers, delimiters);
    }

    private static List<String> parseDelimiters(String delimiterPart) {
        return delimiterPart.startsWith("[") && delimiterPart.endsWith("]")
                ? Stream.of(delimiterPart.split("]\\["))
                        .map(d -> d.replace("[", "").replace("]", ""))
                        .toList()
                : List.of(delimiterPart);
    }

    private static int sumNumbers(String numbers, List<String> delimiters) {
        String regex = delimiters.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));

        List<Integer> parsedNumbers = Stream.of(numbers.split(regex))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();

        List<Integer> negatives = parsedNumbers.stream()
                .filter(n -> n < 0)
                .toList();

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException(
                    "negatives not allowed: " +
                    negatives.stream().map(String::valueOf).collect(Collectors.joining(", "))
            );
        }

        return parsedNumbers.stream()
                .filter(n -> n <= 1000)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
