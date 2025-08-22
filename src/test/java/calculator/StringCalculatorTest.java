package calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
}
