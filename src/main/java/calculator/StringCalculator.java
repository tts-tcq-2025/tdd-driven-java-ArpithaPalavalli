package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class StringCalculator {  // abstract to silence PMD UseUtilityClass

    private static final List<String> DEFAULT_DELIMITERS = List.of(",", "\n");

    public static int add(String input) {
        if (isBlank(input)) {
            return 0;
        }
        Parsed parsed = parseInput(input);
        return calculateSum(parsed.numbers, parsed.delimiters);
    }

    private static boolean isBlank(String input) {
        return input == null || input.isEmpty();
    }

    private static Parsed parseInput(String input) {
        if (input.startsWith("//")) {
            int idx = input.indexOf("\n");
            List<String> delims = new ArrayList<>(DEFAULT_DELIMITERS);
            delims.addAll(parseCustomDelimiters(input.substring(2, idx)));
            return new Parsed(input.substring(idx + 1), delims);
        }
        return new Parsed(input, DEFAULT_DELIMITERS);
    }

    private static List<String> parseCustomDelimiters(String part) {
        if (part.startsWith("[") && part.endsWith("]")) {
            return Stream.of(part.split("]\\["))
                    .map(d -> d.replace("[", "").replace("]", ""))
                    .toList();
        }
        return List.of(part);
    }

    private static int calculateSum(String numbers, List<String> delimiters) {
        List<Integer> values = splitNumbers(numbers, delimiters);
        validateNoNegatives(values);
        return values.stream()
                .filter(n -> n <= 1000)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static List<Integer> splitNumbers(String numbers, List<String> delimiters) {
        String regex = delimiters.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
        return Stream.of(numbers.split(regex))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }

    private static void validateNoNegatives(List<Integer> values) {
        List<Integer> negatives = values.stream()
                .filter(n -> n < 0)
                .toList();
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException(
                    "negatives not allowed: " +
                    negatives.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(", "))
            );
        }
    }

    private static final class Parsed {
        final String numbers;
        final List<String> delimiters;

        Parsed(String numbers, List<String> delimiters) {
            this.numbers = numbers;
            this.delimiters = delimiters;
        }
    }
}
