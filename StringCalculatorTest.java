import org.junit.Test;
import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Test
    public void testEmptyStringReturnsZero() {
        // arrange
        String input = "";
        int expectedValue = 0;

        // action
        int actualValue = StringCalculator.add(input);

        // assert
        assertEquals(expectedValue, actualValue);
    }
}
