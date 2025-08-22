package calculator;

public final class StringCalculator {
     private StringCalculator() {        // private constructor
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Adds numbers from a string input.
     * For TC001: If the input is empty, return 0.
     */
    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0; // ✅ TC001 requirement
        }
        // Not implemented for other cases yet (TDD approach)
        throw new UnsupportedOperationException("Not yet implemented for non-empty input");
    }
}
