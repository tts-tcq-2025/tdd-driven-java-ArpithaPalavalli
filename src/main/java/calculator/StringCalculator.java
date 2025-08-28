package calculator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StringCalculator {
    private static final String DEFAULT_DELIMITER = ",|\\n";

    private StringCalculator() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static int add(String input) {
        return new CalculatorEngine(input).calculate();
    }

    // -------------------- Internal Classes ----------------------

    private static final class CalculatorEngine {
        private final String input;

        CalculatorEngine(String input) {
            this.input = (input == null) ? "" : input;
        }

        int calculate() {
            if (input.isEmpty()) return 0;

            ParsedInput parsed = InputParser.parse(input);
            List<Integer> numbers = NumberExtractor.extract(parsed);

            NegativeNumberValidator.validate(numbers);

            return numbers.stream()
                    .filter(n -> n <= 1000)
                    .mapToInt(Integer::intValue)
                    .sum();
        }
    }

    private static final class InputParser {
        static ParsedInput parse(String input) {
            return input.startsWith("//")
                    ? new CustomDelimiterParser().parse(input)
                    : new DefaultDelimiterParser().parse(input);
        }
    }

    private interface ParserStrategy {
        ParsedInput parse(String input);
    }

    private static final class DefaultDelimiterParser implements ParserStrategy {
        @Override
        public ParsedInput parse(String input) {
            return new ParsedInput(DEFAULT_DELIMITER, input);
        }
    }

    private static final class CustomDelimiterParser implements ParserStrategy {
        @Override
        public ParsedInput parse(String input) {
            int newlineIndex = input.indexOf('\n');
            String spec = input.substring(2, newlineIndex);
            String delimiter = DelimiterBuilder.build(spec) + "|" + DEFAULT_DELIMITER;
            String numbers = input.substring(newlineIndex + 1);
            return new ParsedInput(delimiter, numbers);
        }
    }

    private static final class DelimiterBuilder {
        static String build(String spec) {
            return spec.startsWith("[")
                    ? RegexFromBrackets.build(spec)
                    : java.util.regex.Pattern.quote(spec);
        }
    }

    private static final class RegexFromBrackets {
        static String build(String spec) {
            java.util.regex.Matcher m =
                    java.util.regex.Pattern.compile("\\[(.*?)]").matcher(spec);
            java.util.List<String> delimiters = new java.util.ArrayList<>();
            while (m.find()) {
                delimiters.add(java.util.regex.Pattern.quote(m.group(1)));
            }
            return String.join("|", delimiters);
        }
    }

    private static final class NumberExtractor {
        static List<Integer> extract(ParsedInput parsed) {
            return Stream.of(parsed.numbers().split(parsed.delimiter()))
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
    }

    private static final class NegativeNumberValidator {
        static void validate(List<Integer> numbers) {
            List<Integer> negatives = numbers.stream()
                    .filter(n -> n < 0)
                    .collect(Collectors.toList());
            if (!negatives.isEmpty()) {
                throw new IllegalArgumentException(
                        "negatives not allowed: " +
                                negatives.stream()
                                        .map(String::valueOf)
                                        .collect(Collectors.joining(", "))
                );
            }
        }
    }

    private static final class ParsedInput {
        private final String delimiter;
        private final String numbers;

        ParsedInput(String delimiter, String numbers) {
            this.delimiter = delimiter;
            this.numbers = numbers;
        }

        String delimiter() { return delimiter; }
        String numbers() { return numbers; }
    }
}
