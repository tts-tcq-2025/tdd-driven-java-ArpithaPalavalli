package calculator;
ParsedInput parsed = parseInput(input);
List<Integer> numbers = extractNumbers(parsed.numbers(), parsed.delimiter());


validateNoNegatives(numbers);
return numbers.stream()
.filter(n -> n <= 1000)
.mapToInt(Integer::intValue)
.sum();
}


private static ParsedInput parseInput(String input) {
if (!input.startsWith("//")) {
return new ParsedInput(DEFAULT_DELIMITER, input);
}
int newlineIndex = input.indexOf('\n');
String delimiterSpec = input.substring(2, newlineIndex);
String delimiter = buildDelimiterRegex(delimiterSpec) + "|" + DEFAULT_DELIMITER;
String numbers = input.substring(newlineIndex + 1);
return new ParsedInput(delimiter, numbers);
}


private static String buildDelimiterRegex(String spec) {
if (spec.startsWith("[") && spec.endsWith("]")) {
Matcher m = Pattern.compile("\\[(.*?)]").matcher(spec);
List<String> delimiters = new ArrayList<>();
while (m.find()) {
delimiters.add(Pattern.quote(m.group(1)));
}
return String.join("|", delimiters);
}
return Pattern.quote(spec);
}


private static List<Integer> extractNumbers(String input, String delimiter) {
return Stream.of(input.split(delimiter))
.filter(s -> !s.isEmpty())
.map(Integer::parseInt)
.collect(Collectors.toList());
}


private static void validateNoNegatives(List<Integer> numbers) {
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


private static final class ParsedInput {
private final String delimiter;
private final String numbers;


ParsedInput(String delimiter, String numbers) {
this.delimiter = delimiter;
this.numbers = numbers;
}


String delimiter() {
return delimiter;
}


String numbers() {
return numbers;
}
}
}























// package calculator;

// public final class StringCalculator {
//      private StringCalculator() {        // private constructor
//         throw new AssertionError("Utility class should not be instantiated");
//     }

//     /**
//      * Adds numbers from a string input.
//      * For TC001: If the input is empty, return 0.
//      */
//     public static int add(String input) {
//         if (input == null || input.isEmpty()) {
//             return 0; // ✅ TC001 requirement
//         }
//         // Not implemented for other cases yet (TDD approach)
//         throw new UnsupportedOperationException("Not yet implemented for non-empty input");
//     }
// }
