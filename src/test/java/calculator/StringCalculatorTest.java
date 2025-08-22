package calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Test
    public void testEmptyStringReturnsZero() {
        String input = "";
        int expectedValue = 0;

        int actualValue = StringCalculator.add(input);

        assertEquals(expectedValue, actualValue);
    }
}
