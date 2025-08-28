package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class StringCalculator {

    private StringCalculator() {
        // Prevent instantiation - utility class
    }

    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String numbers = input;
        List<String> delimiters = new ArrayList<>();
        delimiters.add(","); // default delimiter
        delimiters.add("\n");

        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");
            String delimiterPart = input.substring(2, delimiterEndIndex);

            delimiters.addAll(parseDelimiters(delimiterPart));
            numbers = input.substring(delimiterEndIndex + 1);
        }

        return sumNumbers(numbers, delimiters);
    }

    private static List<String> parseDelimiters(String delimiterPart) {
        List<String> delimiters = new ArrayList<>();
        if (delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
            String[] tokens = delimiterPart.split("]\\[");
            for (String token : tokens) {
                delimiters.add(token.replace("[", "").replace("]", ""));
            }
        } else {
            delimiters.add(delimiterPart);
        }
        return delimiters;
    }

    private static int sumNumbers(String numbers, List<String> delimiters) {
        String regex = buildDelimiterRegex(delimiters);
        String[] tokens = numbers.split(regex);

        List<Integer> negatives = new ArrayList<>();
        int sum = 0;

        for (String token : tokens) {
            if (!token.isEmpty()) {
                int num = Integer.parseInt(token);
                if (num < 0) {
                    negatives.add(num);
                } else if (num <= 1000) {
                    sum += num;
                }
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }
        return sum;
    }

    private static String buildDelimiterRegex(List<String> delimiters) {
        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < delimiters.size(); i++) {
            if (i > 0) {
                regex.append("|");
            }
            regex.append(Pattern.quote(delimiters.get(i)));
        }
        return regex.toString();
    }
}
