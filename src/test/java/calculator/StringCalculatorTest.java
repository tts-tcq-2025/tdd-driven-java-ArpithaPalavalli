package calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

    public class StringCalculatorTest {

    @Test
    public void emptyStringInputReturnsZero() {
        // Arrange
        String input = "";
        int expected = 0;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC001 Failed: Empty string should return 0", expected, actual);
    }

    @Test
    public void singleNumberInputReturnsSameNumber() {
        // Arrange
        String input = "1";
        int expected = 1;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC002 Failed: Single number should return itself", expected, actual);
    }

    @Test
    public void twoNumbersInputReturnsSum() {
        // Arrange
        String input = "1,2";
        int expected = 3;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC003 Failed: Two numbers should return sum", expected, actual);
    }

    @Test
    public void multipleNumbersWithCommaAndNewLineReturnsSum() {
        // Arrange
        String input = "1\n2,3";
        int expected = 6;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC004 Failed: Should handle comma and newline", expected, actual);
    }

    @Test
    public void inputWithNewLinesOnlyReturnsSum() {
        // Arrange
        String input = "1\n2\n3";
        int expected = 6;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC005 Failed: Should handle multiple newlines", expected, actual);
    }

    @Test
    public void negativeNumberInputThrowsException() {
        // Arrange
        String input = "1,-2";

        // Act + Assert
        try {
            StringCalculator.add(input);
            fail("TC006 Failed: Exception expected for negative numbers");
        } catch (IllegalArgumentException ex) {
            assertEquals("TC006 Failed", "negatives not allowed: -2", ex.getMessage());
        }
    }

    @Test
    public void multipleNegativeNumbersInputThrowsException() {
        // Arrange
        String input = "1,-2,-3";

        // Act + Assert
        try {
            StringCalculator.add(input);
            fail("TC007 Failed: Exception expected for negative numbers");
        } catch (IllegalArgumentException ex) {
            assertEquals("TC007 Failed", "negatives not allowed: -2, -3", ex.getMessage());
        }
    }

    @Test
    public void numbersGreaterThanThousandAreIgnored() {
        // Arrange
        String input = "2,1001";
        int expected = 2;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC008 Failed: Numbers > 1000 should be ignored", expected, actual);
    }

    @Test
    public void customSingleCharDelimiterReturnsSum() {
        // Arrange
        String input = "//;\n1;2";
        int expected = 3;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC009 Failed: Custom single delimiter should work", expected, actual);
    }

    @Test
    public void customMultiCharDelimiterReturnsSum() {
        // Arrange
        String input = "//[***]\n12***3";
        int expected = 15;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC010 Failed: Multi-character delimiter should work", expected, actual);
    }

    @Test
    public void ignoringLargeNumbersWithCustomDelimiter() {
        // Arrange
        String input = "//;\n2;1001;3";
        int expected = 5;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC011 Failed: Should ignore >1000 with custom delimiter", expected, actual);
    }

    @Test
    public void negativeNumberWithCustomDelimiterThrowsException() {
        // Arrange
        String input = "//;\n1;-2";

        // Act + Assert
        try {
            StringCalculator.add(input);
            fail("TC012 Failed: Exception expected for negative numbers");
        } catch (IllegalArgumentException ex) {
            assertEquals("TC012 Failed", "negatives not allowed: -2", ex.getMessage());
        }
    }

    @Test
    public void mixtureOfDelimitersReturnsSum() {
        // Arrange
        String input = "//;\n1;2\n3,1001";
        int expected = 6;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC013 Failed: Should handle mixture of delimiters", expected, actual);
    }

    @Test
    public void customDelimiterOnlyWithoutNumbersReturnsZero() {
        // Arrange
        String input = "//;\n";
        int expected = 0;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals("TC014 Failed: Empty input with custom delimiter should return 0", expected, actual);
    }
}
