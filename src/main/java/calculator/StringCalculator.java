package calculator;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class StringCalculator {
private StringCalculator() {
throw new AssertionError("Utility class should not be instantiated");
}


/**
* Adds numbers from a string input.
* Supports custom delimiters, ignores numbers > 1000, and throws on negatives.
*/
public static int add(String input) {
if (input == null || input.isEmpty()) {
return 0; // ✅ TC001
}


String delimiter = ",|\\n"; // default delimiters: comma or newline
String numbers = input;


// ✅ Handle custom delimiter syntax
if (input.startsWith("//")) {
Matcher m = Pattern.compile("//(\\[.*?])+|//(.)\\n").matcher(input);
if (m.find()) {
if (m.group(1) != null) { // multi-char delimiters, possibly multiple
Matcher multi = Pattern.compile("\\[(.*?)]").matcher(input);
StringBuilder regex = new StringBuilder();
while (multi.find()) {
if (regex.length() > 0) regex.append("|");
regex.append(Pattern.quote(multi.group(1)));
}
delimiter = regex.toString();
} else if (m.group(2) != null) { // single-char delimiter
delimiter = Pattern.quote(m.group(2));
}
}
numbers = input.substring(input.indexOf("\\n") + 1);
}


// ✅ Split numbers by delimiter(s)
String[] tokens = numbers.split(delimiter);


int sum = 0;
List<Integer> negatives = new ArrayList<>();


for (String token : tokens) {
if (token.isEmpty()) continue;


int num = Integer.parseInt(token);


if (num < 0) {
negatives.add(num);
} else if (num <= 1000) { // ✅ Ignore >1000 (TC008, TC011)
sum += num;
}
}


if (!negatives.isEmpty()) {
throw new IllegalArgumentException("negatives not allowed: " + negatives.toString().replace("[", "").replace("]", ""));
}


return sum;
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
